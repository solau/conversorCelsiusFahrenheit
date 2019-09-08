package com.grupoacert.conversor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EntityScan(basePackageClasses = {ConversorApplication.class, Jsr310JpaConverters.class})
public class ConversorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConversorApplication.class, args);
	}

}
