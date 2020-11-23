package cl.inreqtech.infractech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("cl.inreqtech.infractech.entity")
@SpringBootApplication
public class InfractechApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfractechApplication.class, args);
    }

}
