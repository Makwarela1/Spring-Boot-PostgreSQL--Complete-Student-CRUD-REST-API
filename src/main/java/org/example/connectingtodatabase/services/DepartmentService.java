package org.example.connectingtodatabase.services;

import org.example.connectingtodatabase.dto.DepartmentRequest;
import org.example.connectingtodatabase.dto.DepartmentResponse;

import java.util.List;

public class DepartmentService {
    DepartmentResponse create(DepartmentRequest request);

    List<DepartmentResponse> findAll();

    DepartmentResponse findById(Long id);
}