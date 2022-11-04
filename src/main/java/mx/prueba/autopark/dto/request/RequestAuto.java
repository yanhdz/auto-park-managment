package mx.prueba.autopark.dto.request;

import lombok.Data;
import mx.prueba.autopark.domain.TipoAuto;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
@Data
public class RequestAuto implements Serializable {

    private String marca;
    private String modelo;
    private Integer year;
    private String placa;
    private String tipo;

}
