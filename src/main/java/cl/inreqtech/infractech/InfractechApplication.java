package cl.inreqtech.infractech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EntityScan("cl.inreqtech.infractech.entity")
@SpringBootApplication
@RestController
public class InfractechApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfractechApplication.class, args);
    }

    @GetMapping("/health")
    public ResponseEntity checkHealth() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
