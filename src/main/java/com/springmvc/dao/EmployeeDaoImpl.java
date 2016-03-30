package com.springmvc.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.springmvc.entity.Employee;

public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> createQuery(final String query) {
        final List<?> result = getHibernateTemplate().find(query);
        return (List<Employee>) result;
    }

    @Override
    public void add(final Employee e) {
        getHibernateTemplate().save(e);
    }

    @Override
    public void update(final Employee e) {
        getHibernateTemplate().update(e);
    }

    @Override
    public void delete(final Employee e) {
        getHibernateTemplate().delete(e);
    }

}
