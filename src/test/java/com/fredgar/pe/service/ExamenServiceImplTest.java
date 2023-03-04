package com.fredgar.pe.service;

import com.fredgar.pe.model.Examen;
import com.fredgar.pe.repository.ExamenRepository;
import com.fredgar.pe.repository.PreguntaRepository;
import data.Datos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static data.Datos.EXAMEN_LIST;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ExamenServiceImplTest {

  @Mock
  ExamenRepository examenRepository;
  @Mock
  PreguntaRepository preguntaRepository;
  @InjectMocks
  ExamenServiceImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void buscarExamenPorNombre() {

    when(examenRepository.findAll()).thenReturn(EXAMEN_LIST);
    Optional<Examen> examen = service.buscarExamenPorNombre("Java");
    assertTrue(examen.isPresent());
    assertEquals("Java", examen.get().getNombre());
    assertEquals(3L, examen.orElseThrow().getId());
  }

  @Test
  void buscarExamenPorNombreListaVacia() {

    List<Examen> data = Collections.emptyList();
    when(examenRepository.findAll()).thenReturn(data);
    Optional<Examen> examen = service.buscarExamenPorNombre("Java");
    assertTrue(examen.isEmpty());
    assertFalse(examen.isPresent());
  }

  @Test
  void buscarExamenPorNombreConPreguntasTest() {
    when(examenRepository.findAll()).thenReturn(EXAMEN_LIST);
    when(preguntaRepository.buscarPreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS_LIST);
    Optional<Examen> examen = service.buscarExamenPorNombreConPreguntas("Java");
    assertEquals(4, examen.get().getPreguntas().size());
    assertTrue(examen.get().getPreguntas().contains("POO"));
    assertFalse(examen.get().getPreguntas().contains("Cualquier cosa"));
    assertEquals("POO", examen.get().getPreguntas().get(2));
    assertEquals(true, examen.get().getPreguntas().get(2).equals("POO"));
    assertEquals(true, examen.get().getPreguntas().get(2).equalsIgnoreCase("poo"));
    assertEquals(true, examen.get().getPreguntas().get(2).contains("POO"));
  }

  @Test
  void buscarExamenPorNombreConPreguntasVerifyTest() {
    when(examenRepository.findAll()).thenReturn(EXAMEN_LIST);
    when(preguntaRepository.buscarPreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS_LIST);
    Optional<Examen> examen = service.buscarExamenPorNombreConPreguntas("Java");
    assertEquals(4, examen.get().getPreguntas().size());
    assertTrue(examen.get().getPreguntas().contains("POO"));
    assertFalse(examen.get().getPreguntas().contains("Cualquier cosa"));
    assertEquals("POO", examen.get().getPreguntas().get(2));
    assertEquals(true, examen.get().getPreguntas().get(2).equals("POO"));
    assertEquals(true, examen.get().getPreguntas().get(2).equalsIgnoreCase("poo"));
    assertEquals(true, examen.get().getPreguntas().get(2).contains("POO"));
    verify(examenRepository).findAll();
    verify(preguntaRepository).buscarPreguntasPorExamenId(anyLong());
  }

}