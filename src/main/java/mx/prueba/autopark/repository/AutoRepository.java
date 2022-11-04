package mx.prueba.autopark.repository;

import mx.prueba.autopark.domain.Auto;
import mx.prueba.autopark.domain.TipoAuto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutoRepository extends JpaRepository<Auto,Long> {
    List<Auto> findAllByFkTipoAuto(TipoAuto fkTipoAuto);
    Optional<Auto> findAutoByPlaca(String placa);
}
