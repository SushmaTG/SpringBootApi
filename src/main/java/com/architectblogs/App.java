package com.architectblogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import com.architectblogs.configuration.PersistenceConfiguration;

/**
 * Hello world!
 *
 */

@Import(PersistenceConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.architectblogs"})
//@ComponentScan(basePackages={"com.architectblogs.controller", "com.architectblogs.service", "com.architectblogs.configuration"})
//@EntityScan("com.architectblogs.model")
//@EnableJpaRepositories("com.architectblogs.repository")
public class App extends SpringBootServletInitializer
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
