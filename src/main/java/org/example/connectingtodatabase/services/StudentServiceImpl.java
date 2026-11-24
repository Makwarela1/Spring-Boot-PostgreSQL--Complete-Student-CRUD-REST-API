package org.example.connectingtodatabase.services;

import jakarta.transaction.Transactional;
import org.example.connectingtodatabase.dto.StudentRequest;
import org.example.connectingtodatabase.dto.StudentResponse;
import org.example.connectingtodatabase.entity.Department;
import org.example.connectingtodatabase.entity.Students;
import org.example.connectingtodatabase.exceptions.ResourceNotFoundException;
import org.example.connectingtodatabase.repository.DepartmentRepository;
import org.example.connectingtodatabase.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    public StudentServiceImpl (StudentRepository studentRepository, DepartmentRepository departmentRepository){
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public StudentResponse create (StudentRequest request){
        Department department = departmentRepository.findById(request.departmentId()).orElseThrow(()-> new ResourceNotFoundException("Department not found: " + request.departmentId()));

        Students students = new Students();

        students.setFirstName(request.firstName());
        students.setLastName(request.lastName());
        students.setEmail(request.email());
        students.setDepartment(department);

        Students saved = studentRepository.save(students);

        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentResponse findById (Long id){

    }
}