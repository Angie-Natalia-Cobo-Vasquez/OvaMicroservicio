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
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ova-service")
@RequiredArgsConstructor
@Tag(name = "Controlador de ovas", description = "API para la gestión de ovas")
public class OvaController {

    private final OvaService ovaService;

    @PostMapping("/ovas")
    @Operation(summary = "Crear un nuevo ova")
    @ApiResponse(responseCode = "201", description = "Ova creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    public ResponseEntity<OvaResponse> crearOva(
            @Valid @RequestBody OvaRequest ovaRequest) {
        OvaResponse response = ovaService.crearOva(ovaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/ovas")
    @Operation(summary = "Obtener todas las ovas")
    @ApiResponse(responseCode = "200", description = "Lista de ovas obtenidas exitosamente")
    public ResponseEntity<List<OvaResponse>> listarTodasLasOvas() {
        List<OvaResponse> ovas = ovaService.listarOvas();
        return ResponseEntity.ok(ovas);
    }

    @GetMapping("/ovas/{id}")
    @Operation(summary = "Obtener un ova por ID")
    @ApiResponse(responseCode = "200", description = "Ova encontrado")
    @ApiResponse(responseCode = "404", description = "Ova no encontrado")
    public ResponseEntity<OvaResponse> obtenerOvaPorId(
            @Parameter(description = "ID del ova a buscar")
            @PathVariable Long id) {
        OvaResponse response = ovaService.obtenerOva(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ova/page/{page}")
    @Operation(summary = "Obtener OVA paginados")
    @ApiResponse(responseCode = "200", description = "Página de OVAs obtenida exitosamente")
    public ResponseEntity<Page<OvaResponse>> obtenerOvasPaginados(
            @Parameter(description = "Número de página") @PathVariable int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<OvaResponse> response = ovaService.obtenerOvasPaginados(page, size);
        return ResponseEntity.ok(response);
    }



    @PutMapping("/ovas")
    public ResponseEntity<OvaResponse> actualizarOva(@Valid @RequestBody OvaRequest request) {
        OvaResponse response = ovaService.actualizarOva(request);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/ovas")
    @Operation(summary = "Eliminar todos los ovas")
    @ApiResponse(responseCode = "204", description = "Todos los ovas eliminados exitosamente")
    public ResponseEntity<Void> eliminarTodosLosOvas() {
        ovaService.eliminarTodos();
        return ResponseEntity.noContent().build();
    }
}