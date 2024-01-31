package ru.gb.SpringREST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
//import ru.gb.SpringREST.service.BookService;
import ru.gb.SpringREST.service.BookService;
import ru.gb.SpringREST.service.ReaderService;

@SpringBootApplication
public class SpringRestApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringRestApplication.class, args);

		BookService bookService = context.getBean(BookService.class);

		bookService.init();

		System.out.println();
		System.out.println(bookService.getAllBooks());
		System.out.println();

		ReaderService readerService = context.getBean(ReaderService.class);

		readerService.init();

		System.out.println();
		System.out.println(readerService.getAllReaders());
		System.out.println();
	}

}
