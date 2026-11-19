package org.example.connectingtodatabase.services;
import org.example.connectingtodatabase.dto.StudentRequest;
import org.example.connectingtodatabase.dto.StudentResponse;
import org.example.connectingtodatabase.entity.Students;
import org.example.connectingtodatabase.exceptions.ResourceNotFoundException;
import org.example.connectingtodatabase.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.connectingtodatabase.exceptions.DuplicateResourceException;

import java.util.List;


@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Transactional
    public StudentResponse createStudent(StudentRequest request) {
        if (studentRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("A student with this email already exists");
        }

        Students students = new Students();
        students.setFirstName(request.firstName());
        students.setLastName(request.lastName());
        students.setEmail(request.email());

        Students savedStudent = studentRepository.save(students);

        return mapToResponse(savedStudent);
    }


    @Transactional(readOnly = true)
    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll().stream().map(this::mapToResponse).toList();
    }


    @Transactional(readOnly = true)
    public StudentResponse getStudentById(Long id) {
        Students students = findStudentOrThrow(id);
        return mapToResponse(students);
    }


    @Transactional
    public StudentResponse updateStudent(Long id, StudentRequest request) {
        Students students = findStudentOrThrow(id);

        boolean emailUsedByAnotherStudent = studentRepository.existsByEmailAndIdNot(request.email(), id);

        if (emailUsedByAnotherStudent) {
            throw new DuplicateResourceException("Another student is already using this email");
        }

        students.setFirstName(request.firstName());
        students.setFirstName(request.lastName());
        students.setEmail(request.email());

        Students updatedStudent = studentRepository.save(students);

        return mapToResponse(updatedStudent);
    }


    @Transactional
    public void deleteStudent(Long id) {
        Students students = findStudentOrThrow(id);
        studentRepository.delete(students);
    }

    private Students findStudentOrThrow(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));
    }


    private StudentResponse mapToResponse(Students students) {
        return new StudentResponse(
                students.getId(),
                students.getFirstName(),
                students.getLastName(),
                students.getEmail()
        );
    }

}
