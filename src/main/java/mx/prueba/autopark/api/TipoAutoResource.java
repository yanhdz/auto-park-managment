package mx.prueba.autopark.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mx.prueba.autopark.domain.TipoAuto;
import mx.prueba.autopark.dto.response.ResponseAPI;
import mx.prueba.autopark.service.TipoAutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TipoAutoResource {

    @Autowired
    private TipoAutoService tipoAutoService;

    @PostMapping(value = "/GET/tipo-autos", produces = "application/json")
    @ApiOperation(value = "Servicio que regresa todos los tipos de auto")
    @ApiResponses(value= {
            @ApiResponse(code = 201, message = "Respuesta exitosa"),
            @ApiResponse(code = 403, message = "Sin autorización para usar el servicio"),
            @ApiResponse(code = 500, message = "Error inesperado")
    })
    public ResponseEntity<ResponseAPI> getTipoAutosList(){
        ResponseAPI ResponseAPI = new ResponseAPI("TIPOAUTOS_00","Llamado Correcto",tipoAutoService.getTipoAutos());
        return new ResponseEntity<>(ResponseAPI, HttpStatus.CREATED);
    }

    @PostMapping(value = "/SAVE/tipo-autos", produces = "application/json")
    @ApiOperation(value = "Servicio que realiza el guardado de Tipos de Auto")
    @ApiResponses(value= {
            @ApiResponse(code = 201, message = "Respuesta exitosa"),
            @ApiResponse(code = 403, message = "Sin autorización para usar el servicio"),
            @ApiResponse(code = 500, message = "Error inesperado")
    })
    public ResponseEntity<ResponseAPI> saveTipoAuto(@RequestBody TipoAuto tipoAuto){
        ResponseAPI responseCorpoAPI = new ResponseAPI("TIPOAUTOS_01","Guardado Correcto",tipoAutoService.saveTipoAuto(tipoAuto));
        return new ResponseEntity<>(responseCorpoAPI, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/DELETE/tipo-autos/{idTipoAuto}")
    @ApiOperation(value = "Servicio que borra un Tipo de Auto por ID")
    @ApiResponses(value= {
            @ApiResponse(code = 201, message = "Respuesta exitosa"),
            @ApiResponse(code = 403, message = "Sin autorización para usar el servicio"),
            @ApiResponse(code = 500, message = "Error inesperado")
    })
    public ResponseEntity<?> deleteTipoAuto(@PathVariable Long idTipoAuto){
        if (!tipoAutoService.findById(idTipoAuto).isPresent())
            return new ResponseEntity(new ResponseAPI("TIPOAUTOS_03","Tipo de Auto No encontrado",""), HttpStatus.NOT_FOUND);
        else
            tipoAutoService.removeTipoAuto(idTipoAuto);
            return new ResponseEntity(new ResponseAPI("TIPOAUTOS_02","Borrado Correcto",""), HttpStatus.OK);
    }


}
