package org.mail.box.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@Configuration
@ComponentScan(basePackages = "org.mail.box.**.*")
@EnableAutoConfiguration
@Import(RepositoryRestMvcConfiguration.class)
@ImportResource("META-INF/spring/ApplicationContext.xml")
public class ApplicationInitializer extends SpringBootServletInitializer {
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.showBanner(false)
        		.sources(ApplicationInitializer.class)
                .sources(SpringMongoConfig.class)
                .sources(MvCconfig.class);
    }
    
    public static void main(String[] args) {
    	SpringApplication app = new SpringApplication(ApplicationInitializer.class);
		app.addListeners(new OnStartUp());
		app.run(args);
    }
}