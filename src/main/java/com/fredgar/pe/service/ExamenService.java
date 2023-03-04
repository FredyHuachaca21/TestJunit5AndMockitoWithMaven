package com.fredgar.pe.service;

import com.fredgar.pe.model.Examen;

import java.util.Optional;

public interface ExamenService {
  Optional<Examen> buscarExamenPorNombre(String nombre);
  Optional<Examen> buscarExamenPorNombreConPreguntas(String nombre);
}
