package com.bancolombia.prubea;

import com.bancolombia.prubea.entity.PreguntaE;
import com.bancolombia.prubea.repository.PreguntaERepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.UUID;

@SpringBootApplication
public class PrubeaApplication {

	public static void main(String[] args) {

		SpringApplication.run(PrubeaApplication.class, args);

	}

}
