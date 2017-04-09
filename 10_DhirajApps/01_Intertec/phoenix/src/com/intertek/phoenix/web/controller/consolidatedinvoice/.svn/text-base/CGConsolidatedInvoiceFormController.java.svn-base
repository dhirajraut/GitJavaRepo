package com.intertek.phoenix.web.controller.consolidatedinvoice;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.common.Form;
import com.intertek.web.controller.BaseSimpleFormController;

public class CGConsolidatedInvoiceFormController extends BaseSimpleFormController {

    private static Log log = LogFactory.getLog(CGConsolidatedInvoiceFormController.class);

    public CGConsolidatedInvoiceFormController() {
        super();
        setSessionForm(true);
        setCommandClass(ConsolidateInvoiceForm.class);
    }

    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        return showForm(request, response, errors);
    }

    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Form.getCurrentUserFormat());
        dateFormat.setLenient(false);
        binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(dateFormat, true));
    }

    protected Object formBackingObject(HttpServletRequest request) throws PhoenixException {
        ConsolidateInvoiceForm conslinv = new ConsolidateInvoiceForm();
        return conslinv;
    }
}
