package com.intertek.tool.modifier;

import java.util.Iterator;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;

import com.intertek.entity.JobContract;
import com.intertek.entity.JobOrder;
import com.intertek.entity.JobProd;
import com.intertek.entity.JobProdId;
import com.intertek.entity.JobProdSample;
import com.intertek.entity.JobProdSampleId;
import com.intertek.entity.JobVessel;
import com.intertek.entity.JobVesselId;
import com.intertek.util.Constants;

public class LIMSJobNumberObjectModifier implements IObjectModifier{
	private static final String FLAG="-S";
	
	public Object doModify(Object obj) {
		if(obj!=null && obj instanceof JobOrder){
			//PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("config");
			String envFlag=Constants.evnFlag;//pRB.getString(Constants.ENV_FLAG);
			if(envFlag!=null && !envFlag.equals(Constants.PROD_ENV)){
				JobOrder jobOrder=(JobOrder)obj;
				jobOrder.setJobNumber(jobOrder.getJobNumber()+FLAG);
				modifyJobContracts(jobOrder.getJobContracts());
				modifyJobVessels(jobOrder.getJobVessels());
			}
		}
		return obj;
	}
	
	private void modifyJobContracts(Set<JobContract> objs){
		if(objs==null){
			return;
		}
		Iterator<JobContract> itr=objs.iterator();
		while(itr.hasNext()){
			JobContract obj=itr.next();
			obj.setJobNumber(obj.getJobNumber()+FLAG);
		}
	}
	
	private void modifyJobVessels(Set<JobVessel> objs){
		if(objs==null){
			return;
		}
		Iterator<JobVessel> itr=objs.iterator();
		while(itr.hasNext()){
			JobVessel obj=itr.next();
			JobVesselId vId=obj.getJobVesselId();
			if(vId!=null){
				vId.setJobNumber(vId.getJobNumber()+FLAG);
			}
			modifyJobProds(obj.getJobProds());
		}
	}
	
	private void modifyJobProds(Set<JobProd> objs){
		if(objs==null){
			return;
		}
		Iterator<JobProd> itr=objs.iterator();
		while(itr.hasNext()){
			JobProd obj=itr.next();
			modifyJobProdSamples(obj.getJobProdSamples());
			JobProdId prodId=obj.getJobProdId();
			if(prodId!=null){
				prodId.setJobNumber(prodId.getJobNumber()+FLAG);
			}
		}
	}
	
	private void modifyJobProdSamples(Set<JobProdSample> objs){
		if(objs==null){
			return;
		}
		Iterator<JobProdSample> itr=objs.iterator();
		while(itr.hasNext()){
			JobProdSample obj=itr.next();
			JobProdSampleId sId=obj.getJobProdSampleId();
			if(sId!=null){
				sId.setJobNumber(sId.getJobNumber()+FLAG);
			}
		}
	}
}
