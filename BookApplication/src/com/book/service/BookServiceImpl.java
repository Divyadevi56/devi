package com.book.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.book.bean.Book;
import com.book.dao.BookDao;
import com.book.dao.BookDaoImpl;
import com.book.exception.BookException;



public class BookServiceImpl implements BookService{
    BookDao bookdao=new BookDaoImpl();
	@Override
	public String addBook(Book book) throws BookException, ClassNotFoundException, IOException, SQLException {
		String bookseq;
		bookseq=bookdao.addBook(book);
		return bookseq;
	}

	@Override
	public Book viewBookDetails(String donorId) throws BookException, ClassNotFoundException, IOException, SQLException {
		System.out.println(donorId);
		Book book=new Book();
				book=bookdao.viewBookDetails(donorId);
		
		return book;
		
	}

	@Override
	public List retriveAll() throws BookException, ClassNotFoundException, IOException, SQLException {
		bookdao=new BookDaoImpl();
		List<Book> donorList=null;
		donorList=bookdao.retriveAll();
		return donorList;
		
	}

	public void validateBook(Book book) throws BookException {
		// TODO Auto-generated method stub
        List<String> validationErrors=new ArrayList<String>();
		
		if(!(isValidName(book.getBookName())))
		{
			validationErrors.add("\n book Name should be in Alphabets and minimum 3 characters long\n");
			
		}
		if(!(isValidName(book.getAuthorName())))
		{
			validationErrors.add("\n author Name should be in Alphabets and minimum 3 characters long\n");
			
		}
		if(!(isValidAmount(book.getBookPrice())))
		{
			validationErrors.add("Amount should be positive number");
		}
		
		if(!(validationErrors.isEmpty()))
		{
			throw new BookException(validationErrors+"");
		}
	}

	private boolean isValidAmount(int bookPrice) {
		
		return bookPrice>0;
	}

	private boolean isValidName(String bookName) {
		Pattern p=Pattern.compile("^[A-Z][a-z]{3,}$");
		Matcher phoneMatcher1=p.matcher(bookName);
		return phoneMatcher1.matches();
	}

	
	}

	


