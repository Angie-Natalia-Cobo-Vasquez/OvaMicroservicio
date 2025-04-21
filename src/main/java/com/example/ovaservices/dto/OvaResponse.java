package com.example.ovaservices.dto;

public record OvaResponse(
        Long id,
        String descripcion,
        Long idCurso,
        String nombre
) {}