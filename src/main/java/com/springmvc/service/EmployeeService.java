package com.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.dao.EmployeeDao;
import com.springmvc.entity.Employee;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Transactional
    public List<Employee> getEmployeeList() {
        final StringBuffer sff = new StringBuffer();
        sff.append("select a from ").append(Employee.class.getSimpleName());
        final List<Employee> list = employeeDao.createQuery(sff.toString());
        return list;
    }

    public void save(final Employee e) {
        employeeDao.add(e);
    }

    public void delete(final Employee e) {
        employeeDao.delete(e);
    }
}
