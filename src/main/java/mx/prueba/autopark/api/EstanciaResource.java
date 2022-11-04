package mx.prueba.autopark.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mx.prueba.autopark.domain.Auto;
import mx.prueba.autopark.domain.Estancia;
import mx.prueba.autopark.dto.request.RequestMovimientoAuto;
import mx.prueba.autopark.dto.response.ResponseAPI;
import mx.prueba.autopark.service.AutoService;
import mx.prueba.autopark.service.EstanciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class EstanciaResource {

    @Autowired
    private EstanciaService estanciaService;
    @Autowired
    private AutoService autoService;

    @PostMapping(value = "/registro/entrada", produces = "application/json")
    @ApiOperation(value = "Servicio que realiza La entrada de un auto a Estancia")
    @ApiResponses(value= {
            @ApiResponse(code = 201, message = "Respuesta exitosa"),
            @ApiResponse(code = 403, message = "Sin Estanciarización para usar el servicio"),
            @ApiResponse(code = 500, message = "Error inesperado")
    })
    public ResponseEntity<ResponseAPI> saveEstanciaEntrada(@RequestBody RequestMovimientoAuto movimientoAuto){
        ResponseAPI responseCorpoAPI;
        Auto auto=autoService.findAutoByPlaca(movimientoAuto.getPlaca()).get();
        if(auto!=null){
            if(estanciaService.findEstanciasByAuto(movimientoAuto.getPlaca()).isPresent()){
                Estancia estancia=estanciaService.findEstanciasByAuto(movimientoAuto.getPlaca()).get();
                responseCorpoAPI = new ResponseAPI("EstanciaS_01","El Auto se encuentra dentro de la estancia",estancia);
                return new ResponseEntity<>(responseCorpoAPI, HttpStatus.OK);
            }else{
                Estancia estancia=new Estancia();
                estancia.setFkIdAuto(auto);
                estancia.setFechaSalida(null);
                estancia.setActiva(true);
                responseCorpoAPI = new ResponseAPI("EstanciaS_02","Registro en estancia exitoso",estanciaService.saveEstancia(estancia));
                return new ResponseEntity<>(responseCorpoAPI, HttpStatus.CREATED);
            }
        }else{
            responseCorpoAPI = new ResponseAPI("EstanciaS_03","No se encontró el auto",null);
            return new ResponseEntity<>(responseCorpoAPI, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/registro/salida", produces = "application/json")
    @ApiOperation(value = "Servicio que realiza La salida de un auto de Estancia")
    @ApiResponses(value= {
            @ApiResponse(code = 201, message = "Respuesta exitosa"),
            @ApiResponse(code = 403, message = "Sin Estanciarización para usar el servicio"),
            @ApiResponse(code = 500, message = "Error inesperado")
    })
    public ResponseEntity<ResponseAPI> updateEstancia(@RequestBody RequestMovimientoAuto movimientoAuto){
        ResponseAPI responseCorpoAPI;
        Auto auto=autoService.findAutoByPlaca(movimientoAuto.getPlaca()).get();
        if(auto!=null){
            if(estanciaService.findEstanciasByAuto(movimientoAuto.getPlaca()).isPresent()){
                Estancia estancia=estanciaService.findEstanciasByAuto(movimientoAuto.getPlaca()).get();
                estancia.setActiva(false);
                estancia.setFechaSalida(new Timestamp(new Date().getTime()));
                responseCorpoAPI = new ResponseAPI("EstanciaS_04","El auto ha dejado la estancia, la estancia se ha cerrado",estanciaService.saveEstancia(estancia));
                return new ResponseEntity<>(responseCorpoAPI, HttpStatus.OK);
            }else{
                responseCorpoAPI = new ResponseAPI("EstanciaS_05","No se encontró estancia",null);
                return new ResponseEntity<>(responseCorpoAPI, HttpStatus.NOT_FOUND);
            }
        }else{
            responseCorpoAPI = new ResponseAPI("EstanciaS_06","No se encontró el auto",null);
            return new ResponseEntity<>(responseCorpoAPI, HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping(value = "/GET/estancias", produces = "application/json")
    @ApiOperation(value = "Servicio que realiza el registro  de Estancia")
    @ApiResponses(value= {
            @ApiResponse(code = 201, message = "Respuesta exitosa"),
            @ApiResponse(code = 403, message = "Sin Estanciarización para usar el servicio"),
            @ApiResponse(code = 500, message = "Error inesperado")
    })
    public ResponseEntity<ResponseAPI> getestanciasList(){
        ResponseAPI ResponseAPI = new ResponseAPI("EstanciaS_00","Llamado Correcto",estanciaService.getEstancias());
        return new ResponseEntity<>(ResponseAPI, HttpStatus.CREATED);
    }


    @PostMapping(value = "/SAVE/estancias", produces = "application/json")
    @ApiOperation(value = "Servicio que realiza el guardado de Estancia")
    @ApiResponses(value= {
            @ApiResponse(code = 201, message = "Respuesta exitosa"),
            @ApiResponse(code = 403, message = "Sin Estanciarización para usar el servicio"),
            @ApiResponse(code = 500, message = "Error inesperado")
    })
    public ResponseEntity<ResponseAPI> saveEstancia(@RequestBody Estancia Estancia){
        ResponseAPI responseCorpoAPI = new ResponseAPI("EstanciaS_01","Guardado Correcto",estanciaService.saveEstancia(Estancia));
        return new ResponseEntity<>(responseCorpoAPI, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/DELETE/estancias/{idEstancia}")
    @ApiOperation(value = "Servicio que borra un Estancia por ID")
    @ApiResponses(value= {
            @ApiResponse(code = 201, message = "Respuesta exitosa"),
            @ApiResponse(code = 403, message = "Sin Estanciarización para usar el servicio"),
            @ApiResponse(code = 500, message = "Error inesperado")
    })
    public ResponseEntity<?> deleteEstancia(@PathVariable Long idEstancia){
        if (!estanciaService.findById(idEstancia).isPresent())
            return new ResponseEntity(new ResponseAPI("EstanciaS_03","Estancia No encontrado",""), HttpStatus.NOT_FOUND);
        else
            estanciaService.removeEstancia(idEstancia);
        return new ResponseEntity(new ResponseAPI("EstanciaS_02","Borrado Correcto",""), HttpStatus.OK);
    }


}
