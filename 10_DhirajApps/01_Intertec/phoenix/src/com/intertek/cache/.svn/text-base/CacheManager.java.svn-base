package com.intertek.cache;

import java.util.HashMap;
import java.util.Set;

import org.apache.log4j.Logger;


/**
   CacheManager is designed to be a static interface to a system wide
   (one per VM) cache system.  Multiple cache spaces can be setup (to
   prevent key conflicts when caching unrelated items).
*/
abstract class CacheManager {
	/**
	   System logger.
	*/
	private static Logger log =
		Logger.getLogger("com.intertek.cache.CacheManager");

	/**
	   HashMap used internally to manage multiple Cache objects
	   (with different identifiers).
	*/
	private static HashMap cacheSpace = new HashMap();


	/**
	   Adds an item to the cache for later retrieval to the specified
	   cache.  If cache does not exist, it is created and added to the
	   cache space.
	   @param cacheIdentifier Specifies the cache space to place the entry into
	   @param key Specifies the key the user wishes to store the value
	   under
	   @param value Value to be cached
	*/
	public static void setCacheItem (String cacheIdentifier,
									 String[] key,
									 Object value) {
		if (cacheSpace.containsKey(cacheIdentifier)) {
			Cache cache = (Cache)cacheSpace.get(cacheIdentifier);
			cache.setValue(key, value);
		}
	}

	/**
	   Searches the specified cache space for an entry corresponding
	   to the provided key (if the specified cache is enabled).
	   @param cacheIdentifier Specifies the cache space to be searched
	   @param key Specifies the key to be searched for
	*/
	public static Object getCacheItem (String cacheIdentifier,
									   String[] key) {
		if (cacheSpace.containsKey(cacheIdentifier)) {
			Cache cache = (Cache)cacheSpace.get(cacheIdentifier);
			return cache.getValue(key);
		}
		return null;
	}

	/**
		Specifies whether the cache specified should be active.  When
		a cache is inactive all methods are available, but values will
		not be returned from the cache.
	*/
	public static void enableCache (String cacheIdentifier, boolean enable) {
		if (cacheSpace.containsKey(cacheIdentifier)) {
			Cache cache = (Cache)cacheSpace.get(cacheIdentifier);
			cache.setEnabled(enable);
		}
	}

	public static boolean isCacheEnabled (String cacheIdentifier) {
		if (cacheSpace.containsKey(cacheIdentifier)) {
			Cache cache = (Cache)cacheSpace.get(cacheIdentifier);
			return cache.isEnabled();
		}
		return false;
	}

	public static int getCacheSize (String cacheIdentifier) {
		if (cacheSpace.containsKey(cacheIdentifier)) {
			Cache cache = (Cache)cacheSpace.get(cacheIdentifier);
			return cache.size();
		}
		return 0;
	}

	/**
	   Flushes all cache elements from all caches in the
	   cache space.
	*/
	public static void flushCacheSpace() {
		cacheSpace.clear();
	}

	/**
	   Flushes cache elements from cache specified by
	   cacheIdentifier.
	   @param cacheIdentifier Name of cache to be flushed
	 */
	public static void flushCache (String cacheIdentifier) {
		if (cacheSpace.containsKey(cacheIdentifier)) {
			Cache cache = (Cache)cacheSpace.get(cacheIdentifier);
			cache.flush();
		}
	}

	public static Set getCacheSpaces() {
		return cacheSpace.keySet();
	}

	/**
	   Creates a new cache object for the specified name.  If
	   a cache already exists with that name, the cache is
	   replaced with a new cache object.
	   @param cacheIdentifier Name of cache to be created.
	   @param timeToLive Time (in milliseconds) for entry to live before expiring.  -1
	   specifies that there is no set expiration time.
	   @param maxLatency Time (in milliseconds) that an entry my remain idle (unhit) before
	   it is marked as invalid. -1 specifies that there is no maximum latency.
	   @param maxCacheSize Specifies the maximum size of the cache.  -1 specifies that
	   there is no maximum size.
	   @param enabled Status of cache after its creation.
	*/

	public static void addCache (String cacheIdentifier,
								 long timeToLive, long maxLatency, long maxCacheSize,
								 boolean enabled, boolean allowBulkDelete, double bulkDeletePercent) {
		cacheSpace.put(cacheIdentifier, new Cache(timeToLive, maxLatency, maxCacheSize,
		                                          enabled, allowBulkDelete, bulkDeletePercent));
	}

	/**
		Removes the cache object specified by the identifier provided.
		@param cacheIdentifier Name of the cache to be removed.
	*/
	public static void removeCache (String cacheIdentifier) {
		if (cacheSpace.containsKey(cacheIdentifier)) {
			cacheSpace.remove(cacheIdentifier);
		}
	}
}


