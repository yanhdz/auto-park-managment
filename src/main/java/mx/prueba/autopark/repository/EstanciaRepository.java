package mx.prueba.autopark.repository;

import mx.prueba.autopark.domain.Estancia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EstanciaRepository extends JpaRepository<Estancia,Long> {

    @Query("SELECT e FROM Estancia e JOIN Auto a on e.fkIdAuto.idAuto=a.idAuto WHERE e.activa = true  and a.placa= ?1")
     Optional<Estancia> findEstanciasByAuto(String placa);

    @Query("SELECT e FROM Estancia e JOIN Auto a on e.fkIdAuto.idAuto=a.idAuto JOIN TipoAuto  ta on a.fkTipoAuto.tipo=ta.tipo WHERE  a.placa=:placa and year(e.fechaCreada)=:year and month(e.fechaCreada)=:month and ta.tipo=:tipo")
    List<Estancia> findAllEstanciasByAuto(@Param("placa") String placa, @Param("year") Integer year, @Param("month") Integer month,@Param("tipo") String tipo);

}

