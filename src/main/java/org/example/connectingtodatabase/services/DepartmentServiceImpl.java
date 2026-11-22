package org.example.connectingtodatabase.services;

import org.example.connectingtodatabase.dto.*;
import org.example.connectingtodatabase.entity.Department;
import org.example.connectingtodatabase.exceptions.ResourceNotFoundException;
import org.example.connectingtodatabase.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl extends DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentServiceImpl (
            DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public DepartmentResponse create ( DepartmentRequest request){

        Department department = new Department(request.name(), request.description());

        return  mapToResponse(saved);
    }


    @Override
    public List<DepartmentResponse> findAll(){
        return repository.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    public DepartmentResponse findById(Long id){
        Department department = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department not found: " + id));

        return mapToResponse(department);
    }

    private DepartmentResponse mapToResponse(Department department){
        return new DepartmentResponse(
                department.getId(),
                department.getName(),
                department.getDescription()
        );
    }
}