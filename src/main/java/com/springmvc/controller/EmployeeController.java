package com.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springmvc.entity.Employee;
import com.springmvc.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    protected final transient Log log = LogFactory.getLog(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping
    public String load(final ModelMap modelMap) {
        final List<Employee> list = employeeService.getEmployeeList();
        modelMap.put("list", list);
        return "employee";
    }

    @RequestMapping(params = "method=add")
    public String add(final HttpServletRequest request, final ModelMap modelMap) throws Exception {
        return "employee_add";
    }

    @RequestMapping(params = "method=save")
    public String save(final HttpServletRequest request, final ModelMap modelMap) {
        final String user = request.getParameter("user");
        final String password = request.getParameter("password");
        final Employee employee = new Employee();
        employee.setUser(user);
        employee.setPassword(password);
        try {
            employeeService.save(employee);
            modelMap.put("addstate", "Succeeded");
        } catch (final Exception e) {
            log.error(e.getMessage());
            modelMap.put("addstate", "Failed");
        }

        return "employee_add";
    }

    @RequestMapping(params = "method=delete")
    public void delete(@RequestParam("id") final String id, final HttpServletResponse response) {
        try {
            final Employee employee = new Employee();
            employee.setId(Integer.valueOf(id));
            employeeService.delete(employee);
            response.getWriter().print("{\"delete\":\"true\"}");
        } catch (final Exception e) {
            log.error(e.getMessage());
        }
    }
}
