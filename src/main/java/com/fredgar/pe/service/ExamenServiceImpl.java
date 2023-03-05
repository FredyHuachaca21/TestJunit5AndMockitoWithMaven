package com.fredgar.pe.service;

import com.fredgar.pe.model.Examen;
import com.fredgar.pe.repository.ExamenRepository;
import com.fredgar.pe.repository.PreguntaRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ExamenServiceImpl implements ExamenService{

  private ExamenRepository examenRepository;
  private PreguntaRepository preguntaRepository;

  @Override
  public Optional<Examen> buscarExamenPorNombre(String nombre) {
    return examenRepository.findAll().stream().filter(name -> name.getNombre()
        .equalsIgnoreCase(nombre))
        .findFirst();
  }

  @Override
  public Optional<Examen> buscarExamenPorNombreConPreguntas(String nombre) {
    Optional<Examen> examenOptional = buscarExamenPorNombre(nombre);
    Examen examen = null;
    if(examenOptional.isPresent()) {
      examen = examenOptional.orElseThrow();
      List<String> preguntas = preguntaRepository.buscarPreguntasPorExamenId(examen.getId());
      examen.setPreguntas(preguntas);
    }
    return Optional.ofNullable(examen);
  }

  @Override
  public Examen guardar(Examen examen) {
    if (!examen.getPreguntas().isEmpty()) {
      preguntaRepository.guardarVariasPreguntas(examen.getPreguntas());
    }
    return examenRepository.guardar(examen);
  }
}
