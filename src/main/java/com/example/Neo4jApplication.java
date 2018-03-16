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
import com.example.repository.UserRepository;

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

			User ahmed = new User("ahmed", "pass", "USER", "ahmed@gmail.com", "dakar", "M", "programmer");
			User abdelkabir = new User("abdelkabir", "pass", "USER", "abdelkabir@gmail.com", "stockholm", "M", "programmer");
			User micheal = new User("micheal", "pass", "USER", "micheal@gmail.com","wahran", "M", "taxi driver");
			User hind = new User("hind", "pass", "USER", "hind@gmail.com","tunis", "F", "photographer");
			User sarah = new User("sarah", "pass", "USER", "sarah@gmail.com", "NYC", "F", "freelancer");
			
			User greg = new User("Greg");
			greg.setPassword("pass");
			greg.setRole("User");
			User roy = new User("Roy");
			roy.setPassword("pass");
			roy.setRole("User");
			User craig = new User("Craig");
			craig.setPassword("pass");
			craig.setRole("User");
			
			List<User> team = Arrays.asList(ahmed,hind,abdelkabir,micheal);

			personRepository.save(ahmed);
			personRepository.save(abdelkabir);
			personRepository.save(micheal);
			personRepository.save(hind);
			personRepository.save(sarah);

			abdelkabir = personRepository.findByName(abdelkabir.getName());
			micheal = personRepository.findByName(micheal.getName());
			ahmed = personRepository.findByName(ahmed.getName());
			hind = personRepository.findByName(hind.getName());
			
			TrustRelationship trustee = new TrustRelationship(5,abdelkabir,micheal);
			abdelkabir.addTrustee(trustee);
			personRepository.save(abdelkabir);
			
			TrustRelationship trustee2 = new TrustRelationship(7,micheal,abdelkabir);
			micheal.addTrustee(trustee2);
			personRepository.save(micheal);
			
			TrustRelationship trustee3 = new TrustRelationship(5,hind,micheal);
			TrustRelationship trustee4 = new TrustRelationship(5,hind,ahmed);
			hind.addTrustee(trustee4);
			hind.addTrustee(trustee3);
			personRepository.save(hind);
			

			log.info("Lookup each person by name...");
			team.stream().forEach(person -> log.info(
					"\t" + personRepository.findByName(person.getName()).toString()));
		};
	}
}
