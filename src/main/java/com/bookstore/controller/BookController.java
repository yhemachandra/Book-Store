package com.bookstore.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bookstore.entity.Book;
import com.bookstore.entity.MyBookList;
import com.bookstore.service.BookService;
import com.bookstore.service.MyBookListService;

@Controller
public class BookController
{
	@Autowired
	private BookService service;
	
	@Autowired
	private MyBookListService mybookservice;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/book_register")
	public String bookRegister() {
		return "bookRegister";
	}
	
	@GetMapping("/available_books")
	public ModelAndView getAllBook()
	{
		List<Book> list=service.getAllBook();
		ModelAndView mv=new ModelAndView();
		mv.addObject("book", list);
		mv.setViewName("bookList");
		return mv;
	}
	
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b, RedirectAttributes redirectAttributes) {
	    service.save(b);
	    redirectAttributes.addFlashAttribute("msg", "Book added successfully!");
	    return "redirect:/available_books";
	}

	@GetMapping("/my_books")
	public String getMyBooks(Model model) {
		List<MyBookList> list=mybookservice.getAllMyBooks();
		model.addAttribute("book", list);
		return "myBooks";
	}
	
	@RequestMapping("/mylist/{id}")
		public String getMyList(@PathVariable("id") int id) {
			Book b=service.getBookById(id);
			MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice() );
			mybookservice.saveMyBooks(mb);
			return "redirect:/my_books";
	}
	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id, Model model) {
		Book b=service.getBookById(id);
		model.addAttribute("book", b);
		return "bookEdit";	
	}
	
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		service.deleteById(id);
		return "redirect:/available_books";
	}
	
}

