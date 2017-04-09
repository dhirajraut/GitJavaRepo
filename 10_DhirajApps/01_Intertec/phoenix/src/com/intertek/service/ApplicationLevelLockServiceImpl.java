package com.intertek.service;

import java.util.Date;
import java.util.List;

import com.intertek.dao.Dao;
import com.intertek.entity.ApplicationLevelLock;
import com.intertek.entity.ApplicationLevelLockId;
import com.intertek.exception.LockedByOtherUserException;
import com.intertek.util.Constants;

public class ApplicationLevelLockServiceImpl implements ApplicationLevelLockService{
	private Dao dao;

	public void setDao(Dao dao){
		this.dao = dao;
	}

	public Dao getDao(){
		return dao;
	}

	public ApplicationLevelLock getLock(String name, String key, String userId)throws LockedByOtherUserException{
		ApplicationLevelLock appLock=findLock(name, key);
		if(appLock!=null){
			String lockedByUserId=appLock.getLockedByUser();
			if(lockedByUserId==null || lockedByUserId.trim().isEmpty()){//no one currently locks this object
				appLock.setLockedByUser(userId);
				appLock.setLockedDateTime(new Date(System.currentTimeMillis()));
			}
			else{//it is locked
				if(!lockedByUserId.trim().equalsIgnoreCase(userId)){//some one else beside the requesting user is locking the object
					long lockedTime=appLock.getLockedDateTime().getTime();
					long now=System.currentTimeMillis();
						
					if(now-lockedTime>Constants.JOB_LOCK_TIME_AMOUNT){//check to see if the time has expired
						appLock.setLockedByUser(userId);
						appLock.setLockedDateTime(new Date(System.currentTimeMillis()));
					}
					else{
						throw new LockedByOtherUserException(lockedByUserId);
					}
				}
				else{//re-claim the lock
					appLock.setLockedDateTime(new Date(System.currentTimeMillis()));		
				}
			}
		}
		else{//no one currently locks this object
			appLock=new ApplicationLevelLock(new ApplicationLevelLockId(name, key), userId.trim().toLowerCase(), new Date(System.currentTimeMillis()));
		}
		
		appLock=getDao().save(appLock);
		return appLock;
	}
  
	public void releaseLock(ApplicationLevelLock lock){
		if(lock==null){
			return;
		}
		getDao().remove(lock);
	}

	public List<ApplicationLevelLock> getCurrentLocks(String name) {
		Date aDate=new Date(System.currentTimeMillis()-Constants.JOB_LOCK_TIME_AMOUNT);
		return getDao().find("from ApplicationLevelLock a where a.applicationLevelLockId.name=? and a.lockedDateTime>?", new Object[]{name, aDate});		
	}

	public void releaseLock(String name, String key) {
		releaseLock(findLock(name, key));
	}

	public ApplicationLevelLock findLock(String name, String key) {
		if(name==null || key==null){
			return null;
		}
		
		List<ApplicationLevelLock> list=getDao().find("from ApplicationLevelLock a where a.applicationLevelLockId.name=? and a.applicationLevelLockId.key=?", new Object[]{name, key});
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public void releaseAll(String name) {
	    getDao().bulkUpdate("delete from ApplicationLevelLock a where a.applicationLevelLockId.name=?",
	    		new Object[] {name}
	    );	
	}
}

