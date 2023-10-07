package com.randikalakmal.Springbootelk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
public class SpringbootelkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootelkApplication.class, args);
	}

	Logger logger =  LoggerFactory.getLogger(SpringbootelkApplication.class);

	@GetMapping("/user/{id}")
	public User getUserByID(@PathVariable int id){
		List<User> users = getUsers();

		User user = users.stream().filter(u -> u.getId()==id).findAny().orElse(null);

		if (user != null){
			logger.info("User Details Found : {} ", user);
			return user;
		}else{
			try {
				throw new Exception("User Not Found");
			}catch (Exception e){
				logger.error("User Not Found with id :  {}",id);
			}
			return new User();
		}
	}

	private List<User> getUsers(){
		return Stream.of(new User(1,"Randika"),
						new User(2,"lakmal"),
				new User(3,"Sampath"),
				new User(4,"Test")
				).collect(Collectors.toList());
	}






}
