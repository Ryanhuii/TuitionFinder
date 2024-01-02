package com.ryanhuii.tuitionfinder;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringTuitionFinderApplication {
	// testing github 123
	public static void main(String[] args) {
		// don't use this line.
		// Instead, use the javaFX Application class to run the application
		// SpringApplication.run(SpringTuitionFinderApplication.class, args);
		Application.launch(TuitionFinderApplication.class, args);
	}

}
