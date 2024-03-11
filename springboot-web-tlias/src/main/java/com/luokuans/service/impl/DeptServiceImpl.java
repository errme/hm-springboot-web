package com.luokuans.service.impl;

import com.luokuans.mapper.DeptLogMapper;
import com.luokuans.mapper.DeptMapper;
import com.luokuans.mapper.EmpMapper;
import com.luokuans.pojo.Dept;
import com.luokuans.pojo.DeptLog;
import com.luokuans.service.DeptLogService;
import com.luokuans.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogService deptLogService;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    // @Transactional(rollbackFor = Exception.class) //所有异常都回滚事务 默认运行时异常
    @Transactional
    @Override
    public void delete(Integer id) {
        try {
            deptMapper.deleteById(id); //根据ID删除部门数据

            int i = 1 / 0;
            //if(true){throw new Exception("出错啦...");}

            empMapper.deleteByptId(id); //根据部门ID删除该部门下的员工
        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作,此次解散的是" + id + "号部门");
            deptLogService.insert(deptLog);
        }

    }
}
