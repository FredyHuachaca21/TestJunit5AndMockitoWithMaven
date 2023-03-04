package com.fredgar.pe.repository;

import java.util.List;

public interface PreguntaRepository {
  List<String> buscarPreguntasPorExamenId(Long id);
}
