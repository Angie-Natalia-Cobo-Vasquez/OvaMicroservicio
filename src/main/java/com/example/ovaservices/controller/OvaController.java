package com.example.ovaservices.controller;

import com.example.ovaservices.dto.OvaRequest;
import com.example.ovaservices.dto.OvaResponse;
import com.example.ovaservices.service.OvaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ovas")
@RequiredArgsConstructor
@Tag(name = "Controlador de ovas", description = "API para la gestión de ovas")
public class OvaController {

    private final OvaService ovaService;

    @PostMapping
    @Operation(summary = "Crear un nuevo ova")
    @ApiResponse(responseCode = "201", description = "Ova creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    public ResponseEntity<OvaResponse> crearOva(
            @Valid @RequestBody OvaRequest ovaRequest) {
        OvaResponse response = ovaService.crearOva(ovaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Obtener todas las ovas")
    @ApiResponse(responseCode = "200", description = "Lista de ovas obtenidas exitosamente")
    public ResponseEntity<List<OvaResponse>> listarTodasLasOvas() {
        List<OvaResponse> ovas = ovaService.listarOvas();
        return ResponseEntity.ok(ovas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un ova por ID")
    @ApiResponse(responseCode = "200", description = "Ova encontrado")
    @ApiResponse(responseCode = "404", description = "Ova no encontrado")
    public ResponseEntity<OvaResponse> obtenerOvaPorId(
            @Parameter(description = "ID del ova a buscar")
            @PathVariable Long id) {
        OvaResponse response = ovaService.obtenerOva(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un ova existente")
    @ApiResponse(responseCode = "200", description = "Ova actualizado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    @ApiResponse(responseCode = "404", description = "Ova no encontrado")
    public ResponseEntity<OvaResponse> actualizarOva(
            @Parameter(description = "ID del ova a actualizar")
            @PathVariable Long id,
            @Valid @RequestBody OvaRequest ovaRequest) {
        OvaResponse response = ovaService.actualizarOva(id, ovaRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un ova")
    @ApiResponse(responseCode = "204", description = "Ova eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Ova no encontrado")
    public ResponseEntity<Void> eliminarOva(
            @Parameter(description = "ID del Ova a eliminar")
            @PathVariable Long id) {
        ovaService.eliminarOva(id);
        return ResponseEntity.noContent().build();
    }
}