package com.luokuans.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.luokuans.mapper.EmpMapper;
import com.luokuans.pojo.Emp;
import com.luokuans.pojo.PageBean;
import com.luokuans.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    // public PageBean page(Integer page, Integer pageSize) {
    //
    //     //1. 获取总记录数
    //     Long count = empMapper.count();
    //
    //     //2. 获取分页查询结果列表
    //     Integer start = (page - 1) * pageSize;
    //     List<Emp> empList = empMapper.page(start, pageSize);
    //
    //     //3. 封装PageBean对象
    //     PageBean pageBean = new PageBean(count, empList);
    //     return pageBean;
    // }

    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {

        PageHelper.startPage(page, pageSize);

        List<Emp> empList = empMapper.list(name,gender,begin,end);
        Page<Emp> pg = (Page<Emp>) empList;

        //3. 封装PageBean对象
        PageBean pageBean = new PageBean(pg.getTotal(), pg.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void add(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }
}
