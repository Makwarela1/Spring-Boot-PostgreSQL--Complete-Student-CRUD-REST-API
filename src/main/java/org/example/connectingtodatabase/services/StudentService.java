package org.example.connectingtodatabase.services;

import org.example.connectingtodatabase.dto.StudentRequest;
import org.example.connectingtodatabase.dto.StudentResponse;

import java.util.List;

public interface StudentService {

    StudentResponse create (StudentRequest request);

    List<StudentResponse> findAll();

    StudentResponse findById(Long id);

    List <StudentResponse> findByDepartment(Long departmentId);
}
