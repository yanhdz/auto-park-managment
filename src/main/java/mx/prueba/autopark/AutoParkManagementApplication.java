package mx.prueba.autopark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class AutoParkManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoParkManagementApplication.class, args);
    }

}
