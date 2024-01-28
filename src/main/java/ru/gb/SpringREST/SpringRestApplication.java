package ru.gb.SpringREST;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.gb.SpringREST.model.User;
import ru.gb.SpringREST.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringRestApplication {

	@SneakyThrows
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringRestApplication.class, args);

		UserRepository userRepository = context.getBean(UserRepository.class);

		for (int i = 15; i <= 25; i++) {
			User user = new User();
			user.setId((long) i);
			user.setName("User #" + i);
			user.setAge(i);
			userRepository.save(user);
		}

		Optional<User> foundUser = userRepository.findById(1L);
		foundUser.ifPresent(it -> System.out.println(it));

		userRepository.findById(2L)
				.ifPresentOrElse(it -> System.out.println(it),
						() -> System.out.println("Не найдено пользователя с идентификатором 2"));

		System.out.println();

		System.out.println(userRepository.findAllByName("Eugene"));

		System.out.println();

		Page<User> page = userRepository.findAllByAgeGreaterThan(Pageable.ofSize(3), 20);
		System.out.println(page.getContent());
		System.out.println(page.getTotalPages());
		System.out.println(page.getTotalElements());
		System.out.println(page.getNumber());

		System.out.println();

		System.out.println(userRepository.myquery("User #15"));

		System.out.println();

		System.out.println(userRepository.myquery2().toString());

//		JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
//
//		jdbcTemplate.execute("create table users(id bigint, name varchar(512))");
//		jdbcTemplate.execute("insert into users(id, name) values(1, 'Eugene')");
//
//		List<User> users = jdbcTemplate.query("select id, name from users", new RowMapper<User>() {
//
//			@Override
//			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//				return new User(rs.getLong("id"), rs.getString("name"));
//			}
//		});
//
//		DataSource dataSource = context.getBean(DataSource.class);
//
//		try(Connection connection = dataSource.getConnection()) {
//			try(Statement statement = connection.createStatement()) {
//				statement.execute("create table users(id bigint, name varchar(512))");
//			}
//
//			try(Statement statement = connection.createStatement()) {
//				statement.execute("insert into users(id, name) values(1, 'Eugene')");
//			}
//
//			try(Statement statement = connection.createStatement()) {
//				ResultSet resultSet = statement.executeQuery("select id, name from users");
//				while (resultSet.next()) {
//					System.out.println(resultSet.getInt("id"));
//					System.out.println(resultSet.getString("name"));
//				}
//			}
//		}

	}

//	@Data
//	@AllArgsConstructor
//	static class User {
//		long id;
//		String name;
//
//	}

}
