package org.retailmanager.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring boot application
 * mvn clean package
 * java -jar file 
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"org.retailmanager.*","org.retailmanager.api.*"," org.retailmanager.rest.*"})
//@EnableTransactionManagement   // do this production only
//@EnableCaching   // do this production sonly
public class Application 
{
    public static void main( String[] args )
    {
        SpringApplication.run(Application.class, args);
        
    }
}
