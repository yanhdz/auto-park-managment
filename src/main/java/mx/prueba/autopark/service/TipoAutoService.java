package mx.prueba.autopark.service;

import mx.prueba.autopark.domain.TipoAuto;

import java.util.List;

public interface TipoAutoService {
    TipoAuto saveTipoAuto(TipoAuto tipoAuto);
    void removeTipoAuto(Long id);
    TipoAuto findById(Long id);
    List<TipoAuto> getTipoAutos();
}
