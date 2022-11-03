package mx.prueba.autopark.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TipoAuto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoAuto;
    private String tipo;
    @CreationTimestamp
    @Column(name = "fecha_creada", nullable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date fechaCreada;
    @Column(name = "precio_minuto", nullable = false,columnDefinition="DOUBLE DEFAULT 0")
    private Double precioMinuto;
}
