package mx.prueba.autopark.service;

import mx.prueba.autopark.domain.Estancia;

import java.util.List;
import java.util.Optional;

public interface EstanciaService {
    Estancia saveEstancia(Estancia tipoEstancia);
    void removeEstancia(Long id);
    Optional<Estancia> findById(Long id);
    Optional<Estancia> findEstanciasByAuto(String placa);
    List<Estancia> getEstancias();
}
