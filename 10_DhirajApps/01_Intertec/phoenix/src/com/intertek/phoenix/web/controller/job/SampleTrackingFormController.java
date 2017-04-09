package com.intertek.phoenix.web.controller.job;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.dao.DaoException;

import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.SampleTracking;
import com.intertek.phoenix.job.JobOrderService;
import com.intertek.phoenix.web.controller.ControllerUtil;
import com.intertek.web.controller.BaseSimpleFormController;

public class SampleTrackingFormController extends BaseSimpleFormController {

    public SampleTrackingFormController() {
        super();
        setSessionForm(true);
        setCommandClass(SampleTrackingForm.class);
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        SampleTrackingForm stform = (SampleTrackingForm) command;
        if (request.getParameter("refreshing").equals("addRow")) {
            CEJobContract ceJobContract = ControllerUtil.loadAndUpdate(stform.getJobContract(), CEJobContract.class);
            populateSampleTrackingForms(ceJobContract, stform);
            stform.setRefreshing("none");
        }
        return showForm(request, response, errors);
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        SampleTrackingForm myForm = new SampleTrackingForm();
        String id= request.getParameter("jobContractid");        
        CEJobContract fistJobContract = ControllerUtil.findById(CEJobContract.class, Long.parseLong(id));
        CEJobContract detachedJobContract = ControllerUtil.findById(CEJobContract.class, fistJobContract.getId());
        myForm.setJobContract(detachedJobContract);

        List<SampleTrackDetailForm> stDetailFormList = new ArrayList<SampleTrackDetailForm>();
        for (SampleTracking st : detachedJobContract.getSampleTrackings()) {
            SampleTrackDetailForm stdForm = new SampleTrackDetailForm();
            stdForm.setSampleTracking(st);
            stDetailFormList.add(stdForm);
        }
        myForm.setStDetailFormList(stDetailFormList);
        return myForm;
    }

    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Form.getCurrentUserFormat());
        dateFormat.setLenient(false);
        binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(dateFormat, true));
    }

    private void populateSampleTrackingForms(CEJobContract jobContract, SampleTrackingForm stForm) throws DaoException {
        SampleTrackDetailForm stdForm = new SampleTrackDetailForm();
        JobOrderService joserv = ServiceManager.getJobOrderService();
        SampleTracking st_new = joserv.createSampleTracking(jobContract);
        stdForm.setSampleTracking(st_new);
        stForm.getStDetailFormList().add(stdForm);
    }
}
