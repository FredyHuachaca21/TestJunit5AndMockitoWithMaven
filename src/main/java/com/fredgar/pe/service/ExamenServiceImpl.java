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
  public Optional<Examen> buscarExamenPorNombre(String nombre) {
    return repository.findAll().stream().filter(name -> name.getNombre()
        .equalsIgnoreCase(nombre))
        .findFirst();
  }
}
