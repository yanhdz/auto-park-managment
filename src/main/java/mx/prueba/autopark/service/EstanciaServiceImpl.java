package mx.prueba.autopark.service;

import lombok.extern.slf4j.Slf4j;
import mx.prueba.autopark.domain.Auto;
import mx.prueba.autopark.domain.Estancia;
import mx.prueba.autopark.dto.response.ResponseAPI;
import mx.prueba.autopark.dto.response.ResponseEstanciaCobro;
import mx.prueba.autopark.dto.response.ResponseReporteEstancia;
import mx.prueba.autopark.enums.TipoAutoEnum;
import mx.prueba.autopark.repository.EstanciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static mx.prueba.autopark.util.CalcularPagosUtil.calcularPago;
import static mx.prueba.autopark.util.CalcularPagosUtil.calcularPagoMensual;

@Service
@Transactional
@Slf4j
public class EstanciaServiceImpl implements EstanciaService{

    @Autowired
    private  EstanciaRepository repository;
    @Autowired
    private AutoService autoService;

    @Override
    public ResponseEntity<ResponseAPI> registrarSalida(String placa){
        ResponseAPI responseCorpoAPI;
        ResponseEstanciaCobro responseEstanciaCobro;
        Estancia estancia;
        Auto auto=autoService.findAutoByPlaca(placa).get();
        if(auto!=null){
            if(findEstanciasByAuto(placa).isPresent()){
                //@TODO Logica Para evaluar si se regresa o no el objeto con el cobro
                if(auto.getFkTipoAuto().getTipo().equalsIgnoreCase(TipoAutoEnum.NO_RESIDENTE.getTipo())){
                    estancia=findEstanciasByAuto(placa).get();
                    estancia.setActiva(false);
                    estancia.setFechaSalida(new Timestamp(new Date().getTime()));
                    estancia=saveEstancia(estancia);
                    responseEstanciaCobro=new ResponseEstanciaCobro();
                    responseEstanciaCobro.setFechaCreada(estancia.getFechaCreada());
                    responseEstanciaCobro.setFechaSalida(estancia.getFechaSalida());
                    responseEstanciaCobro.setFkIdAuto(estancia.getFkIdAuto());
                    responseEstanciaCobro.setActiva(estancia.isActiva());
                    responseEstanciaCobro.setPrecioxMinuto(calcularPago(estancia.getFechaCreada(),estancia.getFechaSalida(),auto.getFkTipoAuto().getPrecioMinuto()));
                    responseCorpoAPI = new ResponseAPI("EstanciaS_04","El auto ha dejado la estancia, la estancia se ha cerrado, el Auto tiene que pagar "+String.format("%-14s$%,.2f","",responseEstanciaCobro.getPrecioxMinuto()) ,responseEstanciaCobro);
                    return new ResponseEntity<>(responseCorpoAPI, HttpStatus.OK);
                }else if(auto.getFkTipoAuto().getTipo().equalsIgnoreCase(TipoAutoEnum.EMPRESA.getTipo())){
                    estancia=findEstanciasByAuto(placa).get();
                    estancia.setActiva(false);
                    estancia.setFechaSalida(new Timestamp(new Date().getTime()));
                    responseCorpoAPI = new ResponseAPI("EstanciaS_04","El auto ha dejado la estancia, la estancia se ha cerrado, no tiene que pagar",saveEstancia(estancia));
                    return new ResponseEntity<>(responseCorpoAPI, HttpStatus.OK);
                }else if(auto.getFkTipoAuto().getTipo().equalsIgnoreCase(TipoAutoEnum.RESIDENTE.getTipo())){
                    //@TODO realizar calculos de pago para residente
                    estancia=findEstanciasByAuto(placa).get();
                    estancia.setActiva(false);
                    estancia.setFechaSalida(new Timestamp(new Date().getTime()));
                    responseCorpoAPI = new ResponseAPI("EstanciaS_04","El auto ha dejado la estancia, la estancia se ha cerrado, no tiene que pagar",saveEstancia(estancia));
                    return new ResponseEntity<>(responseCorpoAPI, HttpStatus.OK);
                }else {
                    //@TODO realizar calculos para tipo auto no registrado
                    estancia=findEstanciasByAuto(placa).get();
                    estancia.setActiva(false);
                    estancia.setFechaSalida(new Timestamp(new Date().getTime()));
                    responseCorpoAPI = new ResponseAPI("EstanciaS_04","El auto ha dejado la estancia, la estancia se ha cerrado, no tiene que pagar",saveEstancia(estancia));
                    return new ResponseEntity<>(responseCorpoAPI, HttpStatus.OK);
                }

            }else{
                responseCorpoAPI = new ResponseAPI("EstanciaS_05","No se encontró estancia",null);
                return new ResponseEntity<>(responseCorpoAPI, HttpStatus.NOT_FOUND);
            }
        }else{
            responseCorpoAPI = new ResponseAPI("EstanciaS_06","No se encontró el auto",null);
            return new ResponseEntity<>(responseCorpoAPI, HttpStatus.NOT_FOUND);
        }   
    }

    @Override
    public ResponseEntity<ResponseAPI> registrarEntrada(String placa){
        ResponseAPI responseCorpoAPI;
        Auto auto=autoService.findAutoByPlaca(placa).get();
        if(auto!=null){
            if(findEstanciasByAuto(placa).isPresent()){
                Estancia estancia=findEstanciasByAuto(placa).get();
                responseCorpoAPI = new ResponseAPI("EstanciaS_01","El Auto se encuentra dentro de la estancia",estancia);
                return new ResponseEntity<>(responseCorpoAPI, HttpStatus.OK);
            }else{
                Estancia estancia=new Estancia();
                estancia.setFkIdAuto(auto);
                estancia.setFechaSalida(null);
                estancia.setActiva(true);
                responseCorpoAPI = new ResponseAPI("EstanciaS_02","Registro en estancia exitoso",saveEstancia(estancia));
                return new ResponseEntity<>(responseCorpoAPI, HttpStatus.CREATED);
            }
        }else{
            responseCorpoAPI = new ResponseAPI("EstanciaS_03","No se encontró el auto",null);
            return new ResponseEntity<>(responseCorpoAPI, HttpStatus.NOT_FOUND);
        }
    }
    
    @Override
    public Estancia saveEstancia(Estancia Estancia) {
        log.info("Saving  Estancia {} ",Estancia);
        return repository.save(Estancia);
    }

    @Override
    public void removeEstancia(Long id) {
        log.info("Removing  Estancia {} ",id);
        Optional<Estancia> Estancia=repository.findById(id);
        repository.delete(Estancia.get());
    }

    @Override
    public Optional<Estancia> findById(Long id) {
        log.info("Looking for {}",id);
        return repository.findById(id);
    }

    @Override
    public Optional<Estancia> findEstanciasByAuto(String placa) {
        log.info("Looking for Estancia by placa {}",placa);
        return repository.findEstanciasByAuto(placa);
    }

    @Override
    public List<Estancia> getEstancias() {
        log.info("Fetching all Estancias");
        return repository.findAll();
    }

    @Override
    public ResponseReporteEstancia reporteEstancia(String placa,Integer year,Integer month,String tipoAuto) {
        ResponseReporteEstancia estanciaList=new ResponseReporteEstancia();
        Auto auto=autoService.findAutoByPlaca(placa).get();
        if(auto!=null){
            List<Estancia> estancias=repository.findAllEstanciasByAuto(placa,year,month,tipoAuto);
            if(!estancias.isEmpty()){
                estanciaList.setEstanciaList(estancias);
                estanciaList.setPrecioPagar(calcularPagoMensual(estancias,auto.getFkTipoAuto().getPrecioMinuto()));
                estanciaList.setFkIdAuto(auto);
            }
        }

        return estanciaList;
    }
}
