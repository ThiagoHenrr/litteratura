package com.thi.challenge.litteratura;

import com.thi.challenge.litteratura.App.App;
import com.thi.challenge.litteratura.Repository.AuthorRepository;
import com.thi.challenge.litteratura.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LitteraturaApplication implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LitteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		App app = new App(bookRepository, authorRepository);
		app.showMenu();
	}
}
