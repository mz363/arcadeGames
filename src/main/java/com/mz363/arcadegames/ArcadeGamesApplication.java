package com.mz363.arcadegames;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArcadeGamesApplication {

	private static final Logger logger = LoggerFactory.getLogger(ArcadeGamesApplication.class);

	public static void main(String[] args) {
		logger.info("Init the application...");
		SpringApplication.run(ArcadeGamesApplication.class, args);
	}

}
