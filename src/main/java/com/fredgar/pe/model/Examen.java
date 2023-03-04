package com.fredgar.pe.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Examen {
  private Long id;
  private String nombre;
  private List<String> preguntas;

  public Examen(Long id, String nombre) {
    this.id = id;
    this.nombre = nombre;
    this.preguntas = new ArrayList<>();
  }
}
