package org.example.connectingtodatabase.repository;

import org.example.connectingtodatabase.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
