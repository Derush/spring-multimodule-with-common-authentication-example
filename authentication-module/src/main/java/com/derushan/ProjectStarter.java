/**
 * 
 */
package com.derushan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Derushan Sep 21, 2020
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.derushan.*" })
@EntityScan(basePackages = { "com.derushan.*" })
@EnableJpaRepositories(basePackages = { "com.derushan.*" })
public class ProjectStarter {
	public static void main(String[] args) {
		SpringApplication.run(ProjectStarter.class, args);
	}

}
