package mx.prueba.autopark.service;

import mx.prueba.autopark.domain.Estancia;
import mx.prueba.autopark.dto.response.ResponseAPI;
import mx.prueba.autopark.dto.response.ResponseReporteEstancia;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface EstanciaService {
    Estancia saveEstancia(Estancia tipoEstancia);
    ResponseEntity<ResponseAPI> registrarEntrada(String placa);
    ResponseEntity<ResponseAPI> registrarSalida(String placa);
    void removeEstancia(Long id);
    Optional<Estancia> findById(Long id);
    Optional<Estancia> findEstanciasByAuto(String placa);
    List<Estancia> getEstancias();
    ResponseReporteEstancia reporteEstancia(String placa,Integer year,Integer month,String tipoAuto);
}
