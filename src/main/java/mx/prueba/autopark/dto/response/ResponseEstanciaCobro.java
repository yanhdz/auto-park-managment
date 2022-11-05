package mx.prueba.autopark.dto.response;

import lombok.Data;
import mx.prueba.autopark.domain.Auto;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@SuppressWarnings("serial")
@Data
public class ResponseEstanciaCobro implements Serializable {

    private Date fechaCreada;
    private Timestamp fechaSalida;
    private Auto fkIdAuto;
    private boolean activa;
    private Double precioxMinuto;
}
