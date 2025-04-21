package com.example.ovaservices.service;

import com.example.ovaservices.dto.OvaRequest;
import com.example.ovaservices.dto.OvaResponse;
import com.example.ovaservices.exception.ResourceNotFoundException;
import com.example.ovaservices.model.Ova;
import com.example.ovaservices.repository.OvaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OvaService {

    private final OvaRepository ovaRepository;

    @Transactional
    public OvaResponse crearOva(OvaRequest request) {
        validarDatosOva(request);

        Ova ova = new Ova();
        ova.setNombre(request.nombre());
        ova.setDescripcion(request.descripcion());
        ova.setIdCurso(request.idCurso());
        ova = ovaRepository.save(ova);
        return mapToResponse(ova);
    }

    @Transactional(readOnly = true)
    public List<OvaResponse> listarOvas() {
        return ovaRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OvaResponse obtenerOva(Long id) {
        Ova ova = ovaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ova no encontrado con ID: " + id));
        return mapToResponse(ova);
    }

    @Transactional
    public OvaResponse actualizarOva(Long id, OvaRequest request) {
        validarDatosOva(request);

        Ova ova = ovaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ova no encontrado con ID: " + id));

        ova.setNombre(request.nombre());
        ova.setDescripcion(request.descripcion());
        ova.setIdCurso(request.idCurso());
        ova = ovaRepository.save(ova);
        return mapToResponse(ova);
    }

    @Transactional
    public void eliminarOva(Long id) {
        if (!ovaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Ova no encontrado con ID: " + id);
        }
        ovaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<OvaResponse> listarOvasPorCurso(Long idCurso) {
        return ovaRepository.findByIdCurso(idCurso).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // --- Métodos auxiliares ---
    private OvaResponse mapToResponse(Ova ova) {
        return new OvaResponse(
                ova.getId(),
                ova.getDescripcion(),
                ova.getIdCurso(),
                ova.getNombre()
        );
    }

    private void validarDatosOva(OvaRequest request) {
        if (request.idCurso() < 0) {
            throw new IllegalArgumentException("El id no puede ser negativo");
        }
    }
}