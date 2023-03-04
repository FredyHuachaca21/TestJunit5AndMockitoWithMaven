package com.fredgar.pe.repository;

import com.fredgar.pe.model.Examen;

import java.util.Arrays;
import java.util.List;

public class ExamenRepositoryImpl implements ExamenRepository {
  @Override
  public List<Examen> findAll() {
    return Arrays.asList(
        new Examen(1L, "Fisica"),
        new Examen(2L, "Quimica"),
        new Examen(3L, "Java"),
        new Examen(4L, "Python")
    );
  }
}
