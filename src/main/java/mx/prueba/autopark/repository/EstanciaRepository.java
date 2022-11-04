package mx.prueba.autopark.repository;

import mx.prueba.autopark.domain.Estancia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EstanciaRepository extends JpaRepository<Estancia,Long> {

    @Query("SELECT e FROM Estancia e JOIN Auto a on e.fkIdAuto.idAuto=a.idAuto WHERE e.activa = true  and a.placa= ?1")
     Optional<Estancia> findEstanciasByAuto(String placa);
}
