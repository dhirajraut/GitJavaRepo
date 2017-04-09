package com.intertek.cache;

import java.util.Date;
import org.apache.log4j.Logger;


class CacheObject {
	// Logging Object
	private static Logger log =
		Logger.getLogger("com.intertek.cache.CacheObject");

	// Metadata for reporting and validity checking
	private Date created;
	private Date lastHit;
	private long hitCount;

	// Actual Cache Value
	private Object value;


	CacheObject (Object obj) {
		Date now = new Date();
		created = now;
		lastHit = now;
		hitCount = 0;
		value = obj;
	}


	/**
	   Records that a data hit occured for this entry and returns
	   the cached value.
	*/
	synchronized Object getValueAsHit() {
		lastHit = new Date();
		hitCount++;
		/*log.info("Cache Object Hit :: Total Hit Count = "
				 + hitCount + " :: Last Hit At "
				 + lastHit.toString()
				 + " :: Object Originally Created At "
				 + created.toString());
				 */
		return value;
	}

	Date getCreatedDate() {
		return created;
	}

	Date getLastHitDate() {
		return lastHit;
	}

	long getHitCount() {
		return hitCount;
	}
}
