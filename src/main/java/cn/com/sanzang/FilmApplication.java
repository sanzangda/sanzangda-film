package cn.com.sanzang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication(scanBasePackages = {"cn.com.sanzang"})
@ImportResource("classpath:spring/spring-service-context.xml")
public class FilmApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(FilmApplication.class, args);
	}

}
