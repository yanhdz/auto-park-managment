package mx.prueba.autopark.service;

import mx.prueba.autopark.domain.Auto;
import mx.prueba.autopark.domain.TipoAuto;

import java.util.List;
import java.util.Optional;

public interface AutoService {
    Auto saveAuto(Auto tipoAuto);
    void removeAuto(Long id);
    Optional<Auto> findById(Long id);
    Optional<Auto> findAutoByPlaca(String placa);
    List<Auto> getAutos();
    List<Auto> findAllByTipo(TipoAuto id);
}
