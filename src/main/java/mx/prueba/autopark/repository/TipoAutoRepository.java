package mx.prueba.autopark.repository;

import mx.prueba.autopark.domain.TipoAuto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoAutoRepository extends JpaRepository<TipoAuto,Long> {
    TipoAuto findByTipo(String tipo);
}
