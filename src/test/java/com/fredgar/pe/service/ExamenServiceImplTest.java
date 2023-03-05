package com.fredgar.pe.service;

import com.fredgar.pe.model.Examen;
import com.fredgar.pe.repository.ExamenRepository;
import com.fredgar.pe.repository.PreguntaRepository;
import data.Datos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.*;

import static data.Datos.EXAMEN_LIST;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExamenServiceImplTest {

  @Mock
  ExamenRepository examenRepository;
  @Mock
  PreguntaRepository preguntaRepository;
  @InjectMocks
  ExamenServiceImpl service;


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

  @Test
  void guadarExamenTest() {
    Examen nuevoExamen = Datos.EXAMEN;
    nuevoExamen.setPreguntas(Datos.PREGUNTAS_LIST);
    when(examenRepository.guardar(any(Examen.class))).thenReturn(Datos.EXAMEN);
    Examen examen = service.guardar(nuevoExamen);

    assertNotNull(examen.getId());
    assertEquals(5L, examen.getId());
    assertEquals("JavaScript", examen.getNombre());

    verify(examenRepository).guardar(any(Examen.class));
    verify(preguntaRepository).guardarVariasPreguntas(anyList());
  }

  @Test
  void guadarExamenConIdIncrementalTest() {
    Examen nuevoExamen = Datos.EXAMEN_SIN_ID;
    nuevoExamen.setPreguntas(Datos.PREGUNTAS_LIST);
    when(examenRepository.guardar(any(Examen.class))).then(new Answer<Examen>() {

      Long secuencia = 5L;
      @Override
      public Examen answer(InvocationOnMock invocationOnMock) throws Throwable {
        Examen examen = invocationOnMock.getArgument(0);
        examen.setId(++secuencia);
        return examen;
      }
    });
    Examen examen = service.guardar(nuevoExamen);

    assertNotNull(examen.getId());
    assertEquals(6L, examen.getId());
    assertEquals("JavaScript", examen.getNombre());
    System.out.println(nuevoExamen.getId());
    System.out.println(nuevoExamen.getNombre());
    System.out.println(nuevoExamen.getPreguntas());
    verify(examenRepository).guardar(any(Examen.class));
    verify(preguntaRepository).guardarVariasPreguntas(anyList());
  }
}