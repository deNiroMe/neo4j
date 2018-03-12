package com.example;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.example.domain.TrustRelationship;
import com.example.domain.User;
import com.example.domain.UserRepository;

@SpringBootApplication
@EnableNeo4jRepositories
public class Neo4jApplication {

	private final static Logger log = LoggerFactory.getLogger(Neo4jApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Neo4jApplication.class, args);
	}
	
	@SuppressWarnings("deprecation")
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
	return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
	@Bean
	CommandLineRunner demo(UserRepository personRepository) {
		return args -> {

			personRepository.deleteAll();

			User greg = new User("Greg");
			greg.setPassword("pass");
			greg.setRole("User");
			User roy = new User("Roy");
			roy.setPassword("pass");
			roy.setRole("User");
			User craig = new User("Craig");
			craig.setPassword("pass");
			craig.setRole("User");
			
			List<User> team = Arrays.asList(greg, roy, craig);

			log.info("Before linking up with Neo4j...");

			team.stream().forEach(person -> log.info("\t" + person.toString()));

			personRepository.save(greg);
			personRepository.save(roy);
			personRepository.save(craig);

			greg = personRepository.findByName(greg.getName());
			craig = personRepository.findByName(craig.getName());
			
			TrustRelationship trustee = new TrustRelationship(5,greg,craig);
			greg.addTrustee(trustee);
			personRepository.save(greg);

			
			TrustRelationship trustee2 = new TrustRelationship(5,craig,greg);
			craig.addTrustee(trustee2);
			personRepository.save(craig);
			
//			roy = personRepository.findByName(roy.getName());
//			roy.worksWith(craig);
//			// We already know that roy works with greg
//			personRepository.save(roy);
//
//			// We already know craig works with roy and greg

			log.info("Lookup each person by name...");
			team.stream().forEach(person -> log.info(
					"\t" + personRepository.findByName(person.getName()).toString()));
		};
	}
}
