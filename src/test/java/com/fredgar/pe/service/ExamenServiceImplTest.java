package com.fredgar.pe.service;

import com.fredgar.pe.model.Examen;
import com.fredgar.pe.repository.ExamenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExamenServiceImplTest {

  @Test
  void buscarExamenPorNombre() {
    ExamenRepository repository = mock(ExamenRepository.class);
    List<Examen> data = Arrays.asList(
        new Examen(1L, "Fisica"),
        new Examen(2L, "Quimica"),
        new Examen(3L, "Java"),
        new Examen(4L, "Python")
    );
    when(repository.findAll()).thenReturn(data);
    ExamenService service = new ExamenServiceImpl(repository);
    Optional<Examen> examen = service.buscarExamenPorNombre("Java");
    assertTrue(examen.isPresent());
    assertEquals("Java", examen.get().getNombre());
    assertEquals(3L, examen.orElseThrow().getId());
  }

  @Test
  void buscarExamenPorNombreListaVacia() {
    ExamenRepository repository = mock(ExamenRepository.class);
    List<Examen> data = Collections.emptyList();
    when(repository.findAll()).thenReturn(data);
    ExamenService service = new ExamenServiceImpl(repository);
    Optional<Examen> examen = service.buscarExamenPorNombre("Java");
    assertTrue(examen.isEmpty());
  }
}