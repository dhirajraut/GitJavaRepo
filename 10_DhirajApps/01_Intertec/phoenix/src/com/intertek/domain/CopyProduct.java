package com.intertek.domain;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;

public class CopyProduct
{
    @CascadeValidation

	private String vesselIndex;
    private int targetVesselIndex;
    private String productIndex;
    private String searchForm;
    private int vesselCount;
    private String[] vesselNames;
	public String getProductIndex() {
		return productIndex;
	}
	public void setProductIndex(String productIndex) {
		this.productIndex = productIndex;
	}
	public String getSearchForm() {
		return searchForm;
	}
	public void setSearchForm(String searchForm) {
		this.searchForm = searchForm;
	}
	public String getVesselIndex() {
		return vesselIndex;
	}
	public void setVesselIndex(String vesselIndex) {
		this.vesselIndex = vesselIndex;
	}
	public int getVesselCount() {
		return vesselCount;
	}
	public void setVesselCount(int vesselCount) {
		this.vesselCount = vesselCount;
	}
	public String[] getVesselNames() {
		return vesselNames;
	}
	public void setVesselNames(String[] vesselNames) {
		this.vesselNames = vesselNames;
	}
	public int getTargetVesselIndex() {
		return targetVesselIndex;
	}
	public void setTargetVesselIndex(int targetVesselIndex) {
		this.targetVesselIndex = targetVesselIndex;
	}
	}
