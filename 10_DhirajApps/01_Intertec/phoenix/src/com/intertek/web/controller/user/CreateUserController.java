package com.intertek.web.controller.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.AbstractController;

import com.intertek.entity.User;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.AdminService;
import com.intertek.service.UserService;

public class CreateUserController extends AbstractController {
    private static Log log = LogFactory.getLog(CreateUserController.class);

    /**
     * .ctor
     */
    public CreateUserController() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal
     * (javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse) <context dynamic="true|false">
     * <User Name="jaloia111" /> <Type Name="MANIFEST_FILE" /> </context>
     */
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

        UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");
        AdminService adminService = (AdminService) ServiceLocator.getInstance().getBean("adminService");

        String jobCode = "";
        String supervisorId = "";
        String name = "";
        String permName = "";
        String linkName = "";

        List jobCodes = new ArrayList();
        List superVisorIds = new ArrayList();
        List names = new ArrayList();
        List permNames = new ArrayList();
        List linkNames = new ArrayList();
        List roles = new ArrayList();
        List users = new ArrayList();
        String xml = "";

        if (request.getParameter("jobCode") != null && (!request.getParameter("jobCode").trim().equals(""))) {
            jobCode = request.getParameter("jobCode");
            jobCodes = userService.getJobCodeByCode(jobCode);
            xml = new AjaxXmlBuilder().addItems(jobCodes, "jobCode", "jobCode").toString();
        }

        /*
         * if(request.getParameter("supervisorId") != null &&
         * (!request.getParameter("supervisorId").trim().equals(""))) {
         * supervisorId = request.getParameter("supervisorId"); superVisorIds =
         * userService.getSupervisorById(supervisorId); xml = new
         * AjaxXmlBuilder().addItems(superVisorIds, "supervisorId",
         * "supervisorId").toString();
         * 
         * }
         */

        if (request.getParameter("supervisorId") != null && (!request.getParameter("supervisorId").trim().equals(""))) {
            supervisorId = request.getParameter("supervisorId");
            superVisorIds = userService.getSupervisorByName(supervisorId);
            xml = new AjaxXmlBuilder().addItems(superVisorIds, "loginName", "loginName").toString();
        }

        if (request.getParameter("name") != null && (!request.getParameter("name").trim().equals(""))) {
            name = request.getParameter("name");
            names = adminService.getRolenamesByName(name);
            xml = new AjaxXmlBuilder().addItems(names, "name", "roleDesc").toString();
        }
        if (request.getParameter("permName") != null && (!request.getParameter("permName").trim().equals(""))) {
            permName = request.getParameter("permName");
            permNames = adminService.getPermissionsByName(permName);
            xml = new AjaxXmlBuilder().addItems(permNames, "name", "description").toString();
        }

        if (request.getParameter("linkName") != null && (!request.getParameter("linkName").trim().equals(""))) {
            linkName = request.getParameter("linkName");
            linkNames = adminService.getLinksByName(linkName);
            xml = new AjaxXmlBuilder().addItems(linkNames, "name", "url").toString();
        }
        if (request.getParameter("rolename") != null && (!request.getParameter("rolename").trim().equals(""))) {
            name = request.getParameter("rolename");
            System.out.println("role name is " + name);
            roles = adminService.getRoleAndPermissionsByName(name);
            System.out.println("size of the names" + roles.size());
            xml = new AjaxXmlBuilder().addItems(roles, "name", "roleDesc").toString();
        }
        if (request.getParameter("role") != null && (!request.getParameter("role").trim().equals(""))) {
            name = request.getParameter("role");
            roles = adminService.getRolenamesByName(name);
            xml = new AjaxXmlBuilder().addItems(roles, "name", "roleDesc").toString();
        }
        if (request.getParameter("username") != null && (!request.getParameter("username").trim().equals(""))) {

            name = request.getParameter("username");
            System.out.println("username is " + name);
            users = userService.getUsersByFirstName(name);
            System.out.println("size of the users" + users.size());
            AjaxXmlBuilder xmlBuilder = new AjaxXmlBuilder();
            for (int i = 0; i < users.size(); i++) {
                User user = (User) users.get(i);
                String value = user.getFirstName() + " " + user.getLastName();
                xmlBuilder.addItem(value, value);
            }

            xml = xmlBuilder.toString();
        }

        Map model = new HashMap();
        model.put("Content", xml);

        View view = (View) getApplicationContext().getBean("xmlView");
        return new ModelAndView(view, model);
    }
}
