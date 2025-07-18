package com.hotel.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.hotel.booking.config.DotenvLoader;

@SpringBootApplication
public class BookingApplication {

	public static void main(String[] args) {
		DotenvLoader.loadEnv();
		SpringApplication.run(BookingApplication.class, args);
	}

}
