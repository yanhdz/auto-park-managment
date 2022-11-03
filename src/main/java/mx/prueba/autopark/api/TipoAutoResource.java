package mx.prueba.autopark.api;

import lombok.RequiredArgsConstructor;
import mx.prueba.autopark.domain.TipoAuto;
import mx.prueba.autopark.service.TipoAutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TipoAutoResource {
    private final TipoAutoService tipoAutoService;

    @RequestMapping(value = "/TipoAutos", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<TipoAuto>> getUsers(){
        return ResponseEntity.ok().body(tipoAutoService.getTipoAutos());
    }
}
