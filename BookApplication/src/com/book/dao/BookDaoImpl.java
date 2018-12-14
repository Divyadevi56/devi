package com.book.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.book.bean.Book;
import com.book.exception.BookException;
import com.book.pl.BookMain;
import com.book.util.DBConnection;


public class BookDaoImpl implements BookDao{

	@Override
	public String addBook(Book book) throws BookException, ClassNotFoundException, IOException, SQLException {
		Connection con=DBConnection.getConnection();
		PreparedStatement pst=null;
		//PreparedStatement pst1=null;
		ResultSet resultSet=null;
		String bookId=null;
		int queryResult=0;
		try
		{
		
		 pst=con.prepareStatement("insert into book_detail values(bid_seq.nextval,?,?,?)");
		pst.setString(1,book.getBookName());
		pst.setString(2,book.getAuthorName());
		pst.setInt(3,book.getBookPrice());
		pst.executeUpdate();
		//System.out.println("inserted");
		Statement st=con.createStatement();
		
		resultSet=st.executeQuery("select MAX(bid) from book_detail");
		while(resultSet.next())
		{
			bookId=resultSet.getString(1);
		}
		
		return bookId;
	}catch(SQLException sql)
	{
		System.out.println(sql);
	}
	
		return bookId;
	}

	@Override
	public Book viewBookDetails(String bookName) throws BookException, ClassNotFoundException, IOException, SQLException {
		Connection connection=DBConnection.getConnection();
		ResultSet resultSet=null;
		PreparedStatement pst=null;
		Scanner sc=new Scanner(System.in);
		Book book=new Book();
		
		pst=connection.prepareStatement("select * from book_detail where bname=?");
		 //bookName=sc.nextLine();
		pst.setString(1,bookName);
		resultSet=pst.executeQuery();
		while(resultSet.next())
		{
			
			book.setBookId(resultSet.getString(1));
			book.setBookName(resultSet.getString(2));
			book.setAuthorName(resultSet.getString(3));
			book.setBookPrice(resultSet.getInt(4));
		
		}
		
		
		return book;
		
		
		
		
	}

	@Override
	public List retriveAll() throws BookException, ClassNotFoundException, IOException, SQLException {
		Connection con=DBConnection.getConnection();
		int bookCount = 0;
		
		PreparedStatement ps=null;
		ResultSet resultset = null;
		
		List<Book> bookList=new ArrayList<Book>();
		try
		{
			ps=con.prepareStatement("SELECT * FROM book_detail");
			resultset=ps.executeQuery();
			
			while(resultset.next())
			{	
				Book book=new Book();
				book.setBookId(resultset.getString(1));
				book.setBookName(resultset.getString(2));
				book.setAuthorName(resultset.getString(3));
				book.setBookPrice(resultset.getInt(4));
				
				bookList.add(book);
			bookCount++;
			}			
			
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			
		}
		
		finally
		{
			try 
			{
				resultset.close();
				ps.close();
				con.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
				throw new BookException("Error in closing db connection");

			}
		}
		
		if( bookCount == 0)
			return null;
		else
			return  bookList;
	
	}
  
	

}
