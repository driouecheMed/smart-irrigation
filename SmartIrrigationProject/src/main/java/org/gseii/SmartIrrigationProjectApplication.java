package org.gseii;

import org.gseii.sftp.Watcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class SmartIrrigationProjectApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =SpringApplication.run(SmartIrrigationProjectApplication.class, args);
		 Watcher watcher=context.getBean(Watcher.class);
		 watcher.watch();
	}

}
