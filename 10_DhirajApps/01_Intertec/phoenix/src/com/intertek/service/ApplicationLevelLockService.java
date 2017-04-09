package com.intertek.service;

import java.util.List;

import com.intertek.entity.ApplicationLevelLock;
import com.intertek.exception.LockedByOtherUserException;

public interface ApplicationLevelLockService{
	ApplicationLevelLock findLock(String name, String key);
	ApplicationLevelLock getLock(String name, String key, String userId)throws LockedByOtherUserException;
	void releaseLock(ApplicationLevelLock lock);
	void releaseLock(String name, String key);
	void releaseAll(String name);
	List<ApplicationLevelLock> getCurrentLocks(String name);
}

