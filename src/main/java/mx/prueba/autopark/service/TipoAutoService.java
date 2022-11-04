package mx.prueba.autopark.service;

import mx.prueba.autopark.domain.TipoAuto;

import java.util.List;
import java.util.Optional;

public interface TipoAutoService {
    TipoAuto saveTipoAuto(TipoAuto tipoAuto);
    void removeTipoAuto(Long id);
    Optional<TipoAuto> findById(Long id);
    TipoAuto findByTipo(String tipo);
    List<TipoAuto> getTipoAutos();
}
