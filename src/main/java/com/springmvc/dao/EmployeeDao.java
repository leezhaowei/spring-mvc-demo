package com.springmvc.dao;

import java.util.List;

import com.springmvc.entity.Employee;

public interface EmployeeDao {

    public List<Employee> createQuery(final String query);

    public void add(final Employee e);

    public void update(final Employee e);

    public void delete(final Employee e);
}
