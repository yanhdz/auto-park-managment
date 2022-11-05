package mx.prueba.autopark.dto.response;

import lombok.Data;
import mx.prueba.autopark.domain.Auto;
import mx.prueba.autopark.domain.Estancia;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
@SuppressWarnings("serial")
public class ResponseReporteEstancia implements Serializable {

    private Double precioPagar;
    private String month;
    private String year;
    private Auto fkIdAuto;
    private List<Estancia> estanciaList;
}
