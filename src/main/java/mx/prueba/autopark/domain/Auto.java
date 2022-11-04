package mx.prueba.autopark.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAuto;
    private String marca;
    private String modelo;
    private Integer year;
    @Column(unique=true)
    private String placa;
    @CreationTimestamp
    @Column(name = "fecha_creada", nullable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date fechaCreada;
    @JoinColumn(name = "fk_tipo_auto", referencedColumnName = "idTipoAuto")
    @ManyToOne(optional = false)
    private TipoAuto fkTipoAuto;

}
