package ru.gb.SpringREST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.gb.SpringREST.api.BookController;
//import ru.gb.SpringREST.service.BookService;
import ru.gb.SpringREST.service.BookService2;
import ru.gb.SpringREST.service.ReaderService2;

@SpringBootApplication
public class SpringRestApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringRestApplication.class, args);

		BookService2 bookService = context.getBean(BookService2.class);

		bookService.init();

		System.out.println();
		System.out.println(bookService.getAllBooks());
		System.out.println();

		ReaderService2 readerService = context.getBean(ReaderService2.class);

		readerService.init();

		System.out.println();
		System.out.println(readerService.getAllReaders());
		System.out.println();
	}

}
