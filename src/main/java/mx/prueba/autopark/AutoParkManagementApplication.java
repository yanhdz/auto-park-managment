package mx.prueba.autopark;

import mx.prueba.autopark.domain.TipoAuto;
import mx.prueba.autopark.service.TipoAutoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AutoParkManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoParkManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner run(TipoAutoService tipoAutoService){
        return args ->{
            tipoAutoService.saveTipoAuto(new TipoAuto(1l,"EMPRESA",null,0d));
            tipoAutoService.saveTipoAuto(new TipoAuto(2l,"RESIDENTE",null,0.03));
            tipoAutoService.saveTipoAuto(new TipoAuto(3l,"NO_RESIDENTE",null,0.6));
        };
    }
}
