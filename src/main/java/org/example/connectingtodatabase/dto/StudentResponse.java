package org.example.connectingtodatabase.dto;

public record StudentResponse(
        Long id,
        String firstName,
        String lastName,
        String email
) {
}
