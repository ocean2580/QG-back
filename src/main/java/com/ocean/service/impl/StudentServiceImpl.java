package com.ocean.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocean.entity.Student;
import com.ocean.mapper.StudentMapper;
import com.ocean.service.IStudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {
    @Override
    public Boolean saveStudent(Student student) {

        return saveOrUpdate(student);
    }
}
