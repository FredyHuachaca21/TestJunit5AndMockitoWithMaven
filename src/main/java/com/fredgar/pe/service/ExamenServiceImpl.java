package com.fredgar.pe.service;

import com.fredgar.pe.model.Examen;
import com.fredgar.pe.repository.ExamenRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class ExamenServiceImpl implements ExamenService{

  private ExamenRepository repository;

  @Override
  public Examen buscarExamenPorNombre(String nombre) {
    Optional<Examen> examenOptional = repository.findAll().stream().filter(name -> name.getNombre()
        .equalsIgnoreCase(nombre))
        .findFirst();

    Examen examen = null;
    if (examenOptional.isPresent()) {
      examen = examenOptional.orElseThrow();
    }
    return examen;
  }
}
