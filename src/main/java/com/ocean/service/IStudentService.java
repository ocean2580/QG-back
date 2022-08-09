package com.ocean.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ocean.entity.Student;

public interface IStudentService extends IService<Student> {
    Boolean saveStudent(Student student);
}
