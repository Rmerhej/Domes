	package fr.greta92.Domes;

	import org.springframework.boot.SpringApplication;
	import org.springframework.boot.autoconfigure.SpringBootApplication;
	import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

	@SpringBootApplication(exclude= SecurityAutoConfiguration.class)
	public class DomesApplication {
		public static void main(String[] args) {
			SpringApplication.run(DomesApplication.class, args);
		}

	}
