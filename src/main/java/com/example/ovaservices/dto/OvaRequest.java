package com.example.ovaservices.dto;

import jakarta.validation.constraints.*;

public record OvaRequest(
        @NotNull Long id,
        @NotBlank @Size(max = 500) String descripcion,
        @NotNull Long idCurso,
        @NotBlank String nombre
) {}