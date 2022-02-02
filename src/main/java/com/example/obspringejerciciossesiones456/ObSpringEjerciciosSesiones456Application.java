package com.example.obspringejerciciossesiones456;

import com.example.obspringejerciciossesiones456.entities.Laptop;
import com.example.obspringejerciciossesiones456.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ObSpringEjerciciosSesiones456Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ObSpringEjerciciosSesiones456Application.class, args);
		LaptopRepository laptopRepository = context.getBean(LaptopRepository.class);

		Laptop laptop1 = new Laptop(null, "Toshiba", 999.99, "Core i3", (byte) 4, false, false);
		Laptop laptop2 = new Laptop(null, "Lenovo", 1249.99, "Core i7", (byte) 16, true, true);
		Laptop laptop3 = new Laptop(null, "HP", 1199.99, "Ryzen 5 3400G", (byte) 8, false, true);

		laptopRepository.save(laptop1);
		laptopRepository.save(laptop2);
		laptopRepository.save(laptop3);

	}

}
