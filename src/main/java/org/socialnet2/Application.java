package org.socialnet2;

import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Theme(value = "socialnet2")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
