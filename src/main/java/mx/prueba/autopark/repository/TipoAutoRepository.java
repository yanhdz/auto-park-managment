package mx.prueba.autopark.repository;

import mx.prueba.autopark.domain.TipoAuto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoAutoRepository extends JpaRepository<TipoAuto,Long> {
}
