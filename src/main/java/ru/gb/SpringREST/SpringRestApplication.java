package ru.gb.SpringREST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
//import ru.gb.SpringREST.service.BookService;
import ru.gb.SpringREST.model.Role;
import ru.gb.SpringREST.model.User;
import ru.gb.SpringREST.repository.RoleRepository;
import ru.gb.SpringREST.repository.UserRepository;
import ru.gb.SpringREST.service.BookService;
import ru.gb.SpringREST.service.ReaderService;

@SpringBootApplication
public class SpringRestApplication {

	static long id = 1L;
	static long roleId = 1L;


	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringRestApplication.class, args);

		BookService bookService = context.getBean(BookService.class);
		UserRepository userRepository = context.getBean(UserRepository.class);
		RoleRepository roleRepository = context.getBean(RoleRepository.class);


		bookService.init();

		System.out.println();
		System.out.println(bookService.getAllBooks());
		System.out.println();

		ReaderService readerService = context.getBean(ReaderService.class);

		readerService.init();

		System.out.println();
		System.out.println(readerService.getAllReaders());
		System.out.println();

		saveRole(roleRepository, "admin");
		saveRole(roleRepository, "user");


		saveUser(userRepository, "admin", 1L);
		saveUser(userRepository, "user", 2L);
	}

	private static void saveUser(UserRepository repository, String login, long roleIdInsert) {
		User user = new User();
		user.setId(id++);
		user.setLogin(login);
		user.setPassword(login);
		user.setRoleId(roleIdInsert);
		repository.save(user);
	}

	private static void saveRole(RoleRepository repository, String name) {
		Role role = new Role();
		role.setId(roleId++);
		role.setName(name);
		repository.save(role);
	}

}
