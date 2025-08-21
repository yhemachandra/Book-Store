package com.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;

@Service
public class BookService
{
	@Autowired
	private BookRepository brepo;
	
	public void save(Book b) {
		brepo.save(b);
	}
	
	public List<Book> getAllBook() {
		return brepo.findAll();
	}
	
	public Book getBookById(int id) {
		return brepo.findById(id).get();
	}
	
	public void deleteById(int id) {
		brepo.deleteById(id);
	}
	
}
