package org.retailmanager.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring boot application mvn clean package java -jar file.
 */
@SpringBootApplication
@ComponentScan({"org.retailmanager.*", "org.retailmanager.api.*", "org.retailmanager.rest.*"})
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);

  }
}
