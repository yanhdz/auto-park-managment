package mx.prueba.autopark.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mx.prueba.autopark.domain.Estancia;
import mx.prueba.autopark.dto.request.RequestMovimientoAuto;
import mx.prueba.autopark.dto.response.ResponseAPI;
import mx.prueba.autopark.dto.response.ResponseReporteEstancia;
import mx.prueba.autopark.enums.TipoAutoEnum;
import mx.prueba.autopark.service.EstanciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EstanciaResource {

    @Autowired
    private EstanciaService estanciaService;

    @PostMapping(value = "/reporte/empresa", produces = "application/json")
    @ApiOperation(value = "Servicio que regresa un reporte de las estancias de auto de empresa")
    @ApiResponses(value= {
            @ApiResponse(code = 201, message = "Respuesta exitosa"),
            @ApiResponse(code = 403, message = "Sin Estanciarización para usar el servicio"),
            @ApiResponse(code = 500, message = "Error inesperado")
    })
    public ResponseEntity<ResponseAPI> reporteEmpresa(@RequestBody RequestMovimientoAuto movimientoAuto){
        ResponseReporteEstancia responseReporteEstancia=estanciaService.reporteEstancia(movimientoAuto.getPlaca(),movimientoAuto.getYear(),movimientoAuto.getMonth(),TipoAutoEnum.EMPRESA.getTipo());
        if(responseReporteEstancia.getFkIdAuto()!=null){
            if(responseReporteEstancia.getFkIdAuto().getFkTipoAuto().getTipo().equalsIgnoreCase(TipoAutoEnum.EMPRESA.getTipo())){
                ResponseAPI ResponseAPI = new ResponseAPI("EstanciaS_00","Reporte de estancias de las placas :"+responseReporteEstancia.getFkIdAuto().getPlaca(),responseReporteEstancia);
                return new ResponseEntity<>(ResponseAPI, HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(new ResponseAPI("Estancia_404","Las placas ingresadas no pertenecen a un residente",null), HttpStatus.NOT_FOUND);
            }

        }else{
            return new ResponseEntity<>(new ResponseAPI("Estancia_404","No hay datos",null), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/reporte-mensual/residente", produces = "application/json")
    @ApiOperation(value = "Servicio que regresa un reporte de las estancias de auto de residente")
    @ApiResponses(value= {
            @ApiResponse(code = 201, message = "Respuesta exitosa"),
            @ApiResponse(code = 403, message = "Sin Estanciarización para usar el servicio"),
            @ApiResponse(code = 500, message = "Error inesperado")
    })
    public ResponseEntity<ResponseAPI> reporteResidentes(@RequestBody RequestMovimientoAuto movimientoAuto){
        ResponseReporteEstancia responseReporteEstancia=estanciaService.reporteEstancia(movimientoAuto.getPlaca(),movimientoAuto.getYear(),movimientoAuto.getMonth(),TipoAutoEnum.RESIDENTE.getTipo());
        if(responseReporteEstancia.getFkIdAuto()!=null){
            if(responseReporteEstancia.getFkIdAuto().getFkTipoAuto().getTipo().equalsIgnoreCase(TipoAutoEnum.RESIDENTE.getTipo())){
                ResponseAPI ResponseAPI = new ResponseAPI("EstanciaS_00","Reporte del pago de residentes placas :"+responseReporteEstancia.getFkIdAuto().getPlaca()+", total a pagar: "+String.format("%-14s$%,.2f","",responseReporteEstancia.getPrecioPagar()),responseReporteEstancia);
                return new ResponseEntity<>(ResponseAPI, HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(new ResponseAPI("Estancia_404","Las placas ingresadas no pertenecen a un residente",null), HttpStatus.NOT_FOUND);
            }

        }else{
            return new ResponseEntity<>(new ResponseAPI("Estancia_404","No hay datos",null), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping(value = "/registro/entrada", produces = "application/json")
    @ApiOperation(value = "Servicio que realiza La entrada de un auto a Estancia")
    @ApiResponses(value= {
            @ApiResponse(code = 201, message = "Respuesta exitosa"),
            @ApiResponse(code = 403, message = "Sin Estanciarización para usar el servicio"),
            @ApiResponse(code = 500, message = "Error inesperado")
    })
    public ResponseEntity<ResponseAPI> saveEstanciaEntrada(@RequestBody RequestMovimientoAuto movimientoAuto){
        return estanciaService.registrarEntrada(movimientoAuto.getPlaca());
    }

    @PostMapping(value = "/registro/salida", produces = "application/json")
    @ApiOperation(value = "Servicio que realiza La salida de un auto de Estancia")
    @ApiResponses(value= {
            @ApiResponse(code = 201, message = "Respuesta exitosa"),
            @ApiResponse(code = 403, message = "Sin Estanciarización para usar el servicio"),
            @ApiResponse(code = 500, message = "Error inesperado")
    })
    public ResponseEntity<ResponseAPI> updateEstancia(@RequestBody RequestMovimientoAuto movimientoAuto){
        return estanciaService.registrarSalida(movimientoAuto.getPlaca());
    }


    @PostMapping(value = "/GET/estancias", produces = "application/json")
    @ApiOperation(value = "Servicio que regresa todas las estancias")
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
