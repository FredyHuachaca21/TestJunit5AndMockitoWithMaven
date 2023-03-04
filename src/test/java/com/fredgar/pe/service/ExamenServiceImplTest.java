package com.fredgar.pe.service;

import com.fredgar.pe.model.Examen;
import com.fredgar.pe.repository.ExamenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExamenServiceImplTest {

  ExamenRepository repository;
  ExamenService service;

  @BeforeEach
  void setUp() {
   repository = mock(ExamenRepository.class);
   service = new ExamenServiceImpl(repository);

  }

  @Test
  void buscarExamenPorNombre() {
    List<Examen> data = Arrays.asList(
        new Examen(1L, "Fisica"),
        new Examen(2L, "Quimica"),
        new Examen(3L, "Java"),
        new Examen(4L, "Python")
    );
    when(repository.findAll()).thenReturn(data);
    Optional<Examen> examen = service.buscarExamenPorNombre("Java");
    assertTrue(examen.isPresent());
    assertEquals("Java", examen.get().getNombre());
    assertEquals(3L, examen.orElseThrow().getId());
  }

  @Test
  void buscarExamenPorNombreListaVacia() {

    List<Examen> data = Collections.emptyList();
    when(repository.findAll()).thenReturn(data);
    Optional<Examen> examen = service.buscarExamenPorNombre("Java");
    assertTrue(examen.isEmpty());
    assertFalse(examen.isPresent());
  }
}