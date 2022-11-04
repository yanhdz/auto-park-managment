package mx.prueba.autopark.repository;

import mx.prueba.autopark.domain.Auto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoRepository extends JpaRepository<Auto,Long> {
}
