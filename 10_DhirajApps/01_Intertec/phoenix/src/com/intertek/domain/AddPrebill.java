package com.intertek.domain;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;

import com.intertek.entity.Prebill;
import com.intertek.entity.PrebillHistory;
import com.intertek.entity.PrebillSplit;

public class AddPrebill
{
  @CascadeValidation

  private Prebill prebill;
  private PrebillSplit[] prebillSplits;
  private PrebillHistory[] PrebillHistory;
  private String addOrDeletePrebillSplit;
  private int prebillSplitIndex;
  private String buName;
  private String orgName;
  private String contractCode;
  private String jobContractServiceIndex;
  private String prebillBucket;
  private int prebillBucketIndex;
  private Prebill[] allocPrebills;
  private String prebillIds;
  private String updatePctAllocFlag;
  private String updateBranchDescFlag;
  private String contractIndex;
  private boolean disabledFlag=false;
  private String warnMsg;
  
  	//START: To fix issue  109072
  	private boolean disableEditBrachAlloc;
  
	public boolean isDisableEditBrachAlloc() {
		return disableEditBrachAlloc;
	}
	public void setDisableEditBrachAlloc(boolean disableEditBrachAlloc) {
		this.disableEditBrachAlloc = disableEditBrachAlloc;
	}
	//END: To fix issue  109072
	
	public String getOrgName() {
	return orgName;
}
public void setOrgName(String orgName) {
	this.orgName = orgName;
}
	public Prebill getPrebill() {
		return prebill;
	}
	public void setPrebill(Prebill prebill) {
		this.prebill = prebill;
	}
	public PrebillHistory[] getPrebillHistory() {
		return PrebillHistory;
	}
	public void setPrebillHistory(PrebillHistory[] prebillHistory) {
		PrebillHistory = prebillHistory;
	}
	public PrebillSplit[] getPrebillSplits() {
		return prebillSplits;
	}
	public void setPrebillSplits(PrebillSplit[] prebillSplits) {
		this.prebillSplits = prebillSplits;
	}
	public String getAddOrDeletePrebillSplit() {
		return addOrDeletePrebillSplit;
	}
	public void setAddOrDeletePrebillSplit(String addOrDeletePrebillSplit) {
		this.addOrDeletePrebillSplit = addOrDeletePrebillSplit;
	}
	public int getPrebillSplitIndex() {
		return prebillSplitIndex;
	}
	public void setPrebillSplitIndex(int prebillSplitIndex) {
		prebillSplitIndex = prebillSplitIndex;
	}
	public String getBuName() {
		return buName;
	}
	public void setBuName(String buName) {
		this.buName = buName;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getJobContractServiceIndex() {
		return jobContractServiceIndex;
	}
	public void setJobContractServiceIndex(String jobContractServiceIndex) {
		this.jobContractServiceIndex = jobContractServiceIndex;
	}
	public String getPrebillBucket() {
		return prebillBucket;
	}
	public void setPrebillBucket(String prebillBucket) {
		this.prebillBucket = prebillBucket;
	}
	public int getPrebillBucketIndex() {
		return prebillBucketIndex;
	}
	public void setPrebillBucketIndex(int prebillBucketIndex) {
		this.prebillBucketIndex = prebillBucketIndex;
	}
	public Prebill[] getAllocPrebills() {
		return allocPrebills;
	}
	public void setAllocPrebills(Prebill[] allocPrebills) {
		this.allocPrebills = allocPrebills;
	}
	public String getPrebillIds() {
		return prebillIds;
	}
	public void setPrebillIds(String prebillIds) {
		this.prebillIds = prebillIds;
	}

	public String getUpdatePctAllocFlag() {
		return updatePctAllocFlag;
	}
	public void setUpdatePctAllocFlag(String updatePctAllocFlag) {
		this.updatePctAllocFlag = updatePctAllocFlag;
	}
	public String getUpdateBranchDescFlag() {
		return updateBranchDescFlag;
	}
	public void setUpdateBranchDescFlag(String updateBranchDescFlag) {
		this.updateBranchDescFlag = updateBranchDescFlag;
	}
	public String getContractIndex() {
		return contractIndex;
	}
	public void setContractIndex(String contractIndex) {
		this.contractIndex = contractIndex;
	}
	/**
	 * @return the disabledFlag
	 */
	public boolean isDisabledFlag() {
		return disabledFlag;
	}
	/**
	 * @param disabledFlag the disabledFlag to set
	 */
	public void setDisabledFlag(boolean disabledFlag) {
		this.disabledFlag = disabledFlag;
	}
	/**
	 * @return the warnMsg
	 */
	public String getWarnMsg() {
		return warnMsg;
	}
	/**
	 * @param warnMsg the warnMsg to set
	 */
	public void setWarnMsg(String warnMsg) {
		this.warnMsg = warnMsg;
	}  
}
