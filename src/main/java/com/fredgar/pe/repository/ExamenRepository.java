package com.fredgar.pe.repository;

import com.fredgar.pe.model.Examen;

import java.util.List;

public interface ExamenRepository {

  List<Examen> findAll();
}
