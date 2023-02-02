package bjqrn.hem.sssh_date_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SsshDateWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsshDateWebApplication.class, args);
	}
}
