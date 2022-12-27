package com.ty.jwtOauth;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ty.jwtOauth.dto.User;
import com.ty.jwtOauth.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories
public class JwtOauthApplication {
	
	@Autowired
	private UserRepository repo;
	
	@PostConstruct
	public void initUser() {
		List<User> users = (List<User>) Stream.of(
				new User(101, "eshita", "eshita123", "eeshitamahant@gmail.com"),
				new User(102, "laila", "laila123", "eeshitamahant@gmail.com"))
				.collect(Collectors.toList());
		repo.saveAll(users);
	}

	public static void main(String[] args) {
		SpringApplication.run(JwtOauthApplication.class, args);
	}

}
