package ru.gb.SpringREST;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.gb.SpringREST.model.User;
import ru.gb.SpringREST.repository.UserRepository;

@SpringBootApplication
public class SpringRestApplication {

	static long id = 1L;

	public static void main(String[] args) {
		UserRepository userRepository = SpringApplication.run(SpringRestApplication.class, args)
				.getBean(UserRepository.class);

		saveUser(userRepository, "admin");
		saveUser(userRepository, "user");
		saveUser(userRepository, "auth");
		saveUser(userRepository, "simple");


		SecurityContext securityContext = SecurityContextHolder.getContext();
	}

	private static void saveUser(UserRepository repository, String login) {
		User user = new User();
		user.setId(id++);
		user.setLogin(login);
		user.setPassword(login);
		user.setRole(login);
		repository.save(user);
	}

}
