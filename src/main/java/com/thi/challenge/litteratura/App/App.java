package com.thi.challenge.litteratura.App;

import com.thi.challenge.litteratura.Repository.AuthorRepository;
import com.thi.challenge.litteratura.Repository.BookRepository;
import com.thi.challenge.litteratura.Service.APIConsume;
import com.thi.challenge.litteratura.Service.DataConverter;
import com.thi.challenge.litteratura.model.Author;
import com.thi.challenge.litteratura.model.Book;
import com.thi.challenge.litteratura.model.BookData;
import com.thi.challenge.litteratura.model.BookResults;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {

    private Scanner reading = new Scanner(System.in);
    private APIConsume consume = new APIConsume();
    private final String ADDRESS = "https://gutendex.com/books/?search=";
    DataConverter converter = new DataConverter();

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public App(BookRepository bookRepository, AuthorRepository authorRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void showMenu(){
        int option = -1;
        while(option != 0){
            String menu = """
                    Choose a number: 
                    1 - Search book by title
                    2 - Registered books
                    3 - Registered authors
                    4 - Living authors in a given year
                    5 - Books by idiom
                    
                    0 - exit""";

            System.out.println(menu);
            option = reading.nextInt();
            reading.nextLine();

            switch(option){
                case 1 -> searchBookByTitle();
                case 2 -> getRegisteredBooks();
                case 3 -> getRegisteredAuthors();
                case 4 -> getLivingAuthorsByYear();
                case 5 -> getBooksByIdiom();
                case 0 -> System.out.println("Bye ...");
            }
        }
    }

    public void searchBookByTitle(){
        BookData data = getBookData();
        Book book = new Book(data);

        Optional<Author> optionalAuthorsName = authorRepository.findByName(data.authors().getFirst().name());
        Optional<Book> optionalBooksName = bookRepository.findByTitle(data.title());

        if(optionalAuthorsName.isPresent()){
            if(optionalBooksName.isPresent()){
                System.out.println("""
                        
                        ---------------------------------
                        --- !Book already registered! ---
                        ---------------------------------
                        
                        """);
            } else {
                book.setAuthors(optionalAuthorsName.get());
                bookRepository.save(book);
            }
        } else {
            authorRepository.save(book.getAuthors());
            bookRepository.save(book);
        }
        System.out.println(data);
    }
    private BookData getBookData(){
        System.out.println("Enter the name of the book: ");
        String booksName = reading.nextLine();
        String json = consume.getData(ADDRESS + booksName.replace(' ', '+'));
        BookResults data = converter.getData(json, BookResults.class);
        BookData bookData = data.results()[0];

        return bookData;
    }

    private void getRegisteredBooks(){
        List<Book> books = bookRepository.findAllWithBooks();
        for(Book book: books){
            System.out.println("--------BOOK--------");
            System.out.println(book);
            System.out.println("--------------------\n");
        }
    }

    private void getRegisteredAuthors(){
        List<Author> authors = authorRepository.findAll();
        for(Author author : authors){
            System.out.println("--------AUTHOR--------");
            System.out.println(author);
            System.out.println("----------------------\n");
        }
    }

    private void getLivingAuthorsByYear(){
        System.out.println("Enter the year: ");
        Integer year = reading.nextInt();
        reading.nextLine();

        List<Author> authors = authorRepository.findLivingAuthorsByYear(year);

        if(!authors.isEmpty()){
            for(Author author : authors){
                System.out.println("--------AUTHOR--------");
                System.out.println(author);
                System.out.println("----------------------\n");
            }
        } else{
            System.out.println("Authors not found");
        }
    }

    private void getBooksByIdiom(){
        System.out.println("""
                Enter the idiom
                es - spanish
                en - english
                fr - french
                pt - portuguese:""");
        String idiom = reading.nextLine();

        List<Book> books = bookRepository.findBooksByIdiom(idiom);
        if(!books.isEmpty()){
            for(Book book : books){
                System.out.println("--------BOOK IN " + idiom.toUpperCase() + "--------");
                System.out.println(book);
                System.out.println("----------------------\n");
            }
        } else {
            System.out.println("Books in " + idiom + " not found");
        }
    }
}
