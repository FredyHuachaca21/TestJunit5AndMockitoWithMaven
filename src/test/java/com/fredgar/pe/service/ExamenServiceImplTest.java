package com.fredgar.pe.service;

import com.fredgar.pe.model.Examen;
import com.fredgar.pe.repository.ExamenRepository;
import com.fredgar.pe.repository.ExamenRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

class ExamenServiceImplTest {

  @Test
  void buscarExamenPorNombre() {
    ExamenRepository repository = new ExamenRepositoryImpl();
    ExamenService service = new ExamenServiceImpl(repository);

    Examen examen = service.buscarExamenPorNombre("Java");
    assertNotNull(examen);
    assertEquals("Java", examen.getNombre());
  }
}