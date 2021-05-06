package br.com.luliveira.nu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class LuliveiraApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuliveiraApplication.class, args);
	}

}
