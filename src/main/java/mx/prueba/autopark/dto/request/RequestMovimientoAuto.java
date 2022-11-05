package mx.prueba.autopark.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class RequestMovimientoAuto implements Serializable {
    private String placa;
    private Integer year;
    private Integer month;
}
