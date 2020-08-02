package cn.jiyun.service;

import cn.jiyun.mapper.EmpMapper;
import cn.jiyun.pojo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmpService {
    @Autowired
    private EmpMapper empMapper;

    public void addEmp(Emp emp){
        empMapper.addEmp(emp);
    }

    public List<Emp> findAll(){
        return empMapper.findAll();
    }

    public void delEmpById(Integer eid){
        empMapper.delEmpById(eid);
    }

    public Emp findEmpById(Integer eid){
        return empMapper.findEmpById(eid);
    }

    public void update(Emp emp){
        empMapper.update(emp);
    }
}
