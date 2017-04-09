package com.intertek.domain;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;

import com.intertek.entity.JobManualTest;
import com.intertek.entity.Test;

public class AddTest
{
  @CascadeValidation
  private Test test;

  
  private String div1;
  private String div2;
  //101771 START
  private JobManualTest jobManualTest;
 private Double quantity;
 private Double totalPrice;
 private String flag;
  
  //101771 END
public Double getQuantity() {
	return quantity;
}

public void setQuantity(Double quantity) {
	this.quantity = quantity;
}

public Double getTotalPrice() {
	return totalPrice;
}

public void setTotalPrice(Double totalPrice) {
	this.totalPrice = totalPrice;
}

	public String getDiv1() {
		return div1;
	}

	public void setDiv1(String div1) {
		this.div1 = div1;
	}

	public String getDiv2() {
		return div2;
	}

	public void setDiv2(String div2) {
		this.div2 = div2;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public JobManualTest getJobManualTest() {
		return jobManualTest;
	}

	public void setJobManualTest(JobManualTest jobManualTest) {
		this.jobManualTest = jobManualTest;
	}
}
