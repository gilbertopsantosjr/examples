package org.feed.rss;

import org.feed.rss.config.MvCconfig;
import org.feed.rss.config.SpringMongoConfig;
import org.feed.rss.useful.OnStartUp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Import(RepositoryRestMvcConfiguration.class)
public class Application  {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class, SpringMongoConfig.class, MvCconfig.class);
		app.addListeners(new OnStartUp());
		app.run(args);
	}
}

