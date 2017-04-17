package com.architectblogs.configuration;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.architectblogs.service.StorageService;
import com.architectblogs.service.StorageServiceImpl;

@Configuration
@EnableWebMvc
@EnableJpaRepositories(basePackages = "com.architectblogs.repository",
entityManagerFactoryRef = "entityManagerFactory",
transactionManagerRef = "transactionManager")
@EnableTransactionManagement
public class PersistenceConfiguration extends WebMvcConfigurerAdapter{
	
//	@Autowired
//	StorageService storageService;
	
	@Bean
	public StorageService storageService(){
		return new StorageServiceImpl();
	}
	
//	@Bean
//    public ViewResolver getViewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/public/views/");
//        resolver.setSuffix(".html");
//        return resolver;
//    }

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }  

//	@Bean
//	public FileRepository fileRepository(){
//		return new FileRepositoryimpl();
//	}

	// add the datasource bean, use @Primary annotation if you have multiple datasource definitions
	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	@Primary
	public DataSource dataSource(){
		return DataSourceBuilder.create().build();
	}
	
	// define flyway datasource bean
	@Bean
	@ConfigurationProperties(prefix="datasource.flyway")
	@FlywayDataSource
	public DataSource flywayDataSource(){
		return DataSourceBuilder.create().build();
	}
}
