package mx.prueba.autopark.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estancia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstancia;
    @CreationTimestamp
    @Column(name = "fecha_entrada", nullable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date fechaCreada;
    @Column(name = "fecha_salida")
    private Timestamp fechaSalida;
    @JoinColumn(name = "fk_id_auto", referencedColumnName = "idAuto")
    @ManyToOne(optional = false)
    private Auto fkIdAuto;
    private boolean activa;
}
