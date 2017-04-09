package com.intertek.cache;

import java.util.Comparator;
import java.util.Date;


public class CacheObjectPriorityComparator implements Comparator {

	public int compare(Object obj1, Object obj2) {
		int returnVal = 0;
		CacheObject cacheObj1 = (CacheObject) obj1;
		CacheObject cacheObj2 = (CacheObject) obj2;

		Date co1_lastHitDate = cacheObj1.getLastHitDate();
		Date co2_lastHitDate = cacheObj2.getLastHitDate();
		long co1_hitCount = cacheObj1.getHitCount();
		long co2_hitCount = cacheObj2.getHitCount();

		int dateCompare = co1_lastHitDate.compareTo(co2_lastHitDate);
		int hitCompare = 0;
		if (co1_hitCount < co2_hitCount) {
			hitCompare = -1;
		}
		else if (co1_hitCount < co2_hitCount) {
			hitCompare = 1;
		}

		if (hitCompare == dateCompare) {
			returnVal = hitCompare + dateCompare;
		}
		else {
			returnVal = hitCompare;
		}
		return returnVal;
	}

	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	public static CacheObjectPriorityComparator getInstance() {
		return new CacheObjectPriorityComparator();
	}
}
