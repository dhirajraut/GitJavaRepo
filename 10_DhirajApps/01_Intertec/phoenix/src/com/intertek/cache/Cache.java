package com.intertek.cache;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.log4j.Logger;


/**
   The Cache class describes an instance of a cache.  The cache should
   not be accessed directly, but rather though the CacheManager API.
*/
public class Cache {
	// Logging Object
	private static Logger log =
		Logger.getLogger("com.intertek.cache.Cache");

	// User Defined Parameters
	private long timeToLive;
	private long maxLatency;
	private long maxCacheSize;
	private boolean isEnabled;
	private boolean allowBulkDelete;
	private double bulkDeletePercent;

	// Internal Data Store
	private HashMap cache;

	// Reporting Values
	private long cacheHits;
	private long cacheMisses;
	private Date cacheInitialized;


	Cache (long timeToLive, long maxLatency, long maxCacheSize,
		   boolean enabled, boolean allowBulkDelete, double bulkDeletePercent) {
		this.timeToLive = timeToLive;
		this.maxLatency = maxLatency;
		this.maxCacheSize = maxCacheSize;
		this.isEnabled = enabled;
		this.allowBulkDelete = allowBulkDelete;
		this.bulkDeletePercent = bulkDeletePercent;
		cache = new HashMap();
		cacheHits = 0;
		cacheMisses = 0;
		cacheInitialized = new Date();
	}


	synchronized void setValue(String[] key, Object value) {
		if (isEnabled &&
			((maxCacheSize > 0) || (maxCacheSize == -1))) {
			if (isFull() && !allowBulkDelete) {
				dropEntryByPriority();
			}
			if (isFull() && allowBulkDelete) {
				dropBulkEntries();
			}
			Object cacheKey = generateUniqueKey(key);
			CacheObject cacheObj = new CacheObject(value);
			cache.put(cacheKey, cacheObj);
		}
	}

	Object getValue(String[] key) {
		Object returnVal = null;

		if (isEnabled) {
			String cacheKey = generateUniqueKey(key);
			if (cache.containsKey(cacheKey)) {
				CacheObject cacheObj = (CacheObject)cache.get(cacheKey);

				if (isValid(cacheObj)) {
					recordCacheHit(key, cacheKey);
					returnVal = cacheObj.getValueAsHit();
				}
				else {
					cache.remove(cacheKey);
					recordCacheMiss(key, cacheKey);
				}
			}
			else {
				recordCacheMiss(key, cacheKey);
			}
		}

		return returnVal;
	}

	void setEnabled(boolean enable) {
		this.isEnabled = enable;
	}

	boolean isEnabled() {
		return isEnabled;
	}

	void setAllowBulkDelete(boolean enable) {
		this.isEnabled = enable;
	}

	boolean isAllowedBulkDelete() {
		return allowBulkDelete;
	}

	int size() {
		return cache.size();
	}

	synchronized void flush() {
		cache.clear();
		cacheHits = 0;
		cacheMisses = 0;
		cacheInitialized = new Date();
	}

	private synchronized void dropEntryByPriority() {
		dropInvalidEntries();
		if (isFull()) {
			log.info("Cache Maintainance Invoked... Removing Entry With Least Priority");
			// Sort cache entries by priorty (defined in comparator)
			Object[] entries = cache.values().toArray();
			Arrays.sort(entries,
						CacheObjectPriorityComparator.getInstance());
			Object dropValue = entries[0];

			// Find value with least priority in table
			Iterator itr = cache.keySet().iterator();
			while (itr.hasNext()) {
				Object cacheKey = itr.next();
				CacheObject cacheObj = (CacheObject)cache.get(cacheKey);
				if (cacheObj.equals(dropValue)) {
					//Found entry with least priority - Delete
					cache.remove(cacheKey);
					//log.info("Cache Maintainance Completed... Entry With Least Priority Removed {" + cacheKey + "}");
					return;
				}
			}
		}
	}

	private synchronized void dropInvalidEntries() {
		//log.info("Cache Maintainance Invoked... Removing Invalid Entries");
		Iterator itr = cache.keySet().iterator();
		while (itr.hasNext()) {
			Object cacheKey = itr.next();
			CacheObject cacheObj = (CacheObject)cache.get(cacheKey);
			if (!isValid(cacheObj)) {
				cache.remove(cacheKey);
			}
		}
		//log.info("Cache Maintainance Completed... Invalid Entries Removed");
	}

	private boolean isFull() {
		if ((maxCacheSize > -1) &&
			(cache.size() < maxCacheSize)) {
			return false;
		}
		return true;
	}

	private synchronized void dropBulkEntries() {
		if (isFull()) {
			double totalDeleteCntDbl = (bulkDeletePercent / 100.0) * cache.size();
            int totalDeleteCntInt = new Double(totalDeleteCntDbl).intValue();
			//log.debug(""bulkDeletePercent / 100"");
			log.debug("Abt to delete: " + totalDeleteCntInt);
            //possible loss of data


			Object[] entries = cache.values().toArray();
			Arrays.sort(entries,
						CacheObjectPriorityComparator.getInstance());

            Object[] dropKeys = new Object[totalDeleteCntInt];
			Object[] dropValues = new Object[totalDeleteCntInt];
			for (int i = 0; i < entries.length && i < totalDeleteCntInt; i++) {
				dropValues[i] = entries[i];
			}

			// Find keys to be deleted
			int j = 0;
			Iterator itr = cache.keySet().iterator();
			while (itr.hasNext() && j < totalDeleteCntInt) {
				Object cacheKey = itr.next();
				CacheObject cacheObj = (CacheObject)cache.get(cacheKey);
				if (cacheObj.equals(dropValues[j])) {
					dropKeys[j] = cacheKey;
				}
				j++;
			}

			//Delete the entries
			cache.keySet().removeAll(Arrays.asList(dropKeys));
			dropKeys = null;
		}
	}

	private boolean isValid(CacheObject cacheObj) {
		// Check expiration (if specified)
		if (timeToLive > -1) {
			long cdate = cacheObj.getCreatedDate().getTime();
			long today = (new Date()).getTime();
			if ((today - cdate) >= timeToLive) {
				return false;
			}
		}
		// Check maximum latency (if specified)
		if (maxLatency > -1) {
			long cdate = cacheObj.getLastHitDate().getTime();
			long today = (new Date()).getTime();
			if ((today - cdate) >= maxLatency) {
				return false;
			}
		}
		return true;
	}

	private void recordCacheHit(String[] key, String cacheKey) {
		cacheHits++;
		/**
		log.info("Cache Hit For CacheKey {" + cacheKey + "} UserKey {" + key + "}\n"
				 + "Total Cache Hits = " + cacheHits
				 + " :: Total Cache Misses = " + cacheMisses
				 + " :: Cache Last Initialized At " + cacheInitialized);
		**/
	}

	private void recordCacheMiss(String[] key, String cacheKey) {
		cacheMisses++;
		/**
		log.info("Cache Miss For CacheKey {" + cacheKey + "} UserKey {" + key + "}\n"
				 + "Total Cache Hits = " + cacheHits
				 + " :: Total Cache Misses = " + cacheMisses
				 + " :: Cache Last Initialized At " + cacheInitialized);
		**/
	}

	private static String generateUniqueKey(String[] key) {
		try {
			StringBuffer sb = new StringBuffer();
			MessageDigest md = MessageDigest.getInstance("MD5");
			for (int i = 0; i < key.length; i++) {
				md.update(key[i].getBytes());
				sb.append(toHexString(md.digest()));
				md.reset();
			}
			md.update(sb.toString().getBytes());
			return toHexString(md.digest());
		}
		catch (NoSuchAlgorithmException nsae) {
			throw new RuntimeException("MD5 Algorithm Provided By JDK/JRE Not Found");
		}
	}

    private static final char hexChars[] = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

    private static String toHexString (byte bytes[]) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			hexString.append(hexChars[(bytes[i] & 0xF0) >> 4]);
			hexString.append(hexChars[bytes[i] & 0x0F]);
		}
		return hexString.toString();
    }
}

