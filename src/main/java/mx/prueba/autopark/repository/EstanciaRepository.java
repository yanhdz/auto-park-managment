package mx.prueba.autopark.repository;

import mx.prueba.autopark.domain.Estancia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstanciaRepository extends JpaRepository<Estancia,Long> {
}
