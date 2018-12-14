package com.book.pl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.book.bean.Book;
import com.book.exception.BookException;
import com.book.service.BookService;
import com.book.service.BookServiceImpl;



public class BookMain {
	
	
	static Scanner sc=new Scanner(System.in);
	
	private static  BookServiceImpl bookServiceImpl;
	
	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException, BookException {
		 Book book=new Book();                                             
		 BookService bookService=null;
		 BookServiceImpl bookServiceImpl=null;
		 Book book1=null;
		 String bookId=null;
			
			System.out.println();
			System.out.println();
			System.out.println(" Online Book Store");
			System.out.println("1.Admin");
			System.out.println("2.User");
			System.out.println("enter choice");
			
			int ch=sc.nextInt();
			switch(ch)
			{
			case 1: 
			      
				int option=0;
				while(true)
				{
				
			System.out.println("1.Add Book");
			System.out.println("2.View BookDetails");
			System.out.println("3.Retrive All books");
			System.out.println("4.Exit");
			System.out.println("________________________________________\n");
			System.out.println("Select an option:");
			try
			{
				 option = sc.nextInt();
				switch(option) {
				case 1: 
					while(book1==null)
					{
						book1=populateBookDetails();
					}
					try {
						bookService=new BookServiceImpl();
						 bookId = bookService.addBook(book1);
							
							System.out.println("book details has been successfully registered");
							System.out.println("book Id is:"+bookId);
							
						}
						catch(BookException bookException)
						{
							bookException.printStackTrace();
							System.out.println("ERROR:"+bookException.getMessage());
						}
						finally
						{
							bookId=null;
							bookService=null;
							book1=null;
						}
						
						break;
				default: System.out.println("Try Again!!!!!!!");
				
				break;
				}
			}catch(Exception e)
			{
				e.printStackTrace();
				System.out.println(e);
			}
				}
			case 2:  System.out.println("1.Book details");
			         System.out.println("2.cart");
			         int c=0;
			         System.out.println("enter choice");
			         c=sc.nextInt();
			         switch(c)
			         {
			         case 1:
			        	   bookService = new BookServiceImpl();
							try {
								List<Book> bookList = new ArrayList<Book>();
								bookList = bookService.retriveAll();
 
								if (bookList != null) {
									Iterator<Book> i = bookList.iterator();
									while (i.hasNext()) {
										System.out.println(i.next());
									}
								} else {
									System.out.println("No books available");
								}

							}

							catch (BookException e) {

								e.printStackTrace();
							}

							break;
			         case 2: System.out.println("enter book name");
			         String bookName = sc.next();
						bookService=new BookServiceImpl();
						book=bookService.viewBookDetails(bookName);
						System.out.println(book);
						System.out.println("if you want to order press 1 otherwise press 0");
						int i=sc.nextInt();
						 switch(i)
						 {
						 case 1: 
						 }
			         }
			}
		
	}
			
	private static Book populateBookDetails() {
		Book book=new Book();
		System.out.println("enter bookname");
		book.setBookName(sc.next());
		
		System.out.println("enter author name");
		book.setAuthorName(sc.next());
		
		
		System.out.println("enter book price");
		//int bookPrice=sc.nextInt();
		
		try {
			
			
			book.setBookPrice(sc.nextInt());
			}
			catch(InputMismatchException ime)
			{
				sc.nextLine();
				System.out.println("please enter a numeric value for price,try again");
			}
		bookServiceImpl=new BookServiceImpl();
		
		try 
		{
			bookServiceImpl.validateBook(book);
			return book;
		}
		catch(BookException bookException)
		{
			
			System.err.println("invalid data");
			System.err.println(bookException.getMessage()+"\n try again");
			System.exit(0);
			bookException.printStackTrace();
		}
		
return book;
		
	}
	

}
