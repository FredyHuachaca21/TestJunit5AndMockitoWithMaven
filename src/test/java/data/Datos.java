package data;

import com.fredgar.pe.model.Examen;

import java.util.Arrays;
import java.util.List;

public class Datos {
  public final static List<Examen> EXAMEN_LIST = Arrays.asList(
      new Examen(1L, "Fisica"),
      new Examen(2L, "Quimica"),
      new Examen(3L, "Java"),
      new Examen(4L, "Python")
  );

  public final static List<String> PREGUNTAS_LIST = Arrays.asList(
    "Integrales", "Elementos Quimicos", "POO", "Funciones"
  );

  public final static Examen EXAMEN = new Examen(5L, "JavaScript");
  public final static Examen EXAMEN_SIN_ID = new Examen(5L, "JavaScript");
}
