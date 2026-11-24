package org.example.connectingtodatabase.services;

import org.example.connectingtodatabase.dto.DepartmentResponse;
import org.example.connectingtodatabase.dto.DepartmentRequest;

import java.util.List;

public interface DepartmentService {
    DepartmentResponse create(DepartmentRequest request);

    List<DepartmentResponse> findAll();

    DepartmentResponse findById(Long id);
}
