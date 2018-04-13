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

import com.example.domain.Product;
import com.example.domain.Recommendation;
import com.example.domain.TrustRelationship;
import com.example.domain.User;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;

@SuppressWarnings("deprecation")
@SpringBootApplication
@EnableNeo4jRepositories
public class Neo4jApplication {

	private final static Logger log = LoggerFactory.getLogger(Neo4jApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Neo4jApplication.class, args);
	}
	
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
	return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	CommandLineRunner demo(UserRepository personRepository,ProductRepository productRepository) {
		return args -> {

			personRepository.deleteAll();
			productRepository.deleteAll();

			User ahmed = new User("ahmed", "pass", "USER", "ahmed@gmail.com", "dakar", "M", "programmer","img/avatar1.png");
			User abdelkabir = new User("abdelkabir", "pass", "USER", "abdelkabir@gmail.com", "stockholm", "M", "programmer","img/avatar2.png");
			User micheal = new User("micheal", "pass", "USER", "micheal@gmail.com","wahran", "M", "taxi driver","img/avatar4.png");
			User hind = new User("hind", "pass", "USER", "hind@gmail.com","tunis", "F", "photographer","img/avatar5.png");
			User sarah = new User("sarah", "pass", "USER", "sarah@gmail.com", "NYC", "F", "freelancer","img/avatar6.png");
			User omar = new User("omar", "pass", "USER", "omar@gmail.com","tokyo", "M", "commercial supervisor","img/avatar3.png");
			User abdeljalil = new User("abd eljalil", "pass", "USER", "abdeljalilh@gmail.com", "ben grir", "M", "i'm rich i don't work","img/avatar10.png");
			
			List<User> team = Arrays.asList(ahmed,hind,abdelkabir,micheal);

			personRepository.save(ahmed);
			personRepository.save(abdelkabir);
			personRepository.save(micheal);
			personRepository.save(hind);
			personRepository.save(sarah);
			personRepository.save(omar);
			personRepository.save(abdeljalil);

			abdelkabir = personRepository.findByName(abdelkabir.getName());
			micheal = personRepository.findByName(micheal.getName());
			ahmed = personRepository.findByName(ahmed.getName());
			hind = personRepository.findByName(hind.getName());
			omar = personRepository.findByName(omar.getName());
			abdeljalil = personRepository.findByName(abdeljalil.getName());
			
			TrustRelationship trustee = new TrustRelationship(5,abdelkabir,micheal);
			abdelkabir.addTrustee(trustee);
			personRepository.save(abdelkabir);
			
			TrustRelationship trustee0 = new TrustRelationship(5,omar,abdeljalil);
			omar.addTrustee(trustee0);
			personRepository.save(omar);
			
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
			
			Product car = new Product("Acura TLX Base 4dr Front-wheel Drive Sedan", "Cars", "$4000,00",(short) 8);
			Product laptop = new Product("Xiaomi Mi Laptop Notebook Air i5 13.3 8GB DDR4 RAM 256GB SSD", "Laptops", "$654,50",(short) 7);
			Product bag = new Product("Business Water Resistant Polyester Laptop Backpack with USB Charging", "Bags", "$1,8",(short) 6);
			Product bike = new Product("Hot light carbon fiber 18 speed road bike", "Bikes", "$799",(short) 5);
			Product boat = new Product("INTEX Mariner 3 Fishing Type 3 person Inflatable Raft River/Lake Dinghy Boat For Fishing", "Boats", "$1000,00",(short) 4);
			Product phone = new Product("Original in stock Asus Zenfone 2 ZE551ML 4GB RAM 32GB ROM", "phones", "$40,50",(short) 3);
			Product accessories = new Product("Wood Hard Case Bamboo+PC Phone Case Accesoriesfor Iphone 6 plus", "accesories", "$4,6",(short) 2);
			Product moto = new Product("Motocycle - Scrambler 125 EEC Retro motorcycle 180cc", "motocycles", "$1200,5",(short) 1);
			
			productRepository.save(car);
			productRepository.save(laptop);
			productRepository.save(bag);
			productRepository.save(bike);
			productRepository.save(boat);
			productRepository.save(phone);
			productRepository.save(accessories);
			productRepository.save(moto);
			
			Recommendation r1 = new Recommendation(moto, ahmed, (short) 3);
			ahmed.addRecommendation(r1);
			personRepository.save(ahmed);
			
			Recommendation r2 = new Recommendation(boat, micheal, (short) 2);
			micheal.addRecommendation(r2);
			personRepository.save(micheal);			
			
			Recommendation r3 = new Recommendation(phone, abdeljalil, (short) 4);
			abdeljalil.addRecommendation(r3);
			personRepository.save(abdeljalil);
			
			Recommendation r4 = new Recommendation(phone, abdelkabir, (short) 2);
			abdelkabir.addRecommendation(r4);
			personRepository.save(abdelkabir);
			
		};
	}
}
