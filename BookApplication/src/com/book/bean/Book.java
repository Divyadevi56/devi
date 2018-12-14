package com.book.bean;

public class Book {
	    String bookId;
		String bookName;
		String authorName;
		int bookPrice;



		
		public String getBookId() {
			return bookId;
		}
		public void setBookId(String bookId) {
			this.bookId = bookId;
		}
		public String getBookName() {
			return bookName;
		}
		public void setBookName(String bookName) {
			this.bookName = bookName;
		}
		
		public String getAuthorName() {
			return authorName;
		}
		public void setAuthorName(String authorName) {
			this.authorName = authorName;
		}
		public int getBookPrice() {
			return bookPrice;
		}
		public void setBookPrice(int bookPrice) {
			this.bookPrice = bookPrice;
		}
		@Override
		public String toString() {
			return "Book [bookId=" + bookId + ", bookName=" + bookName + ", authorName=" + authorName + ", bookPrice="
					+ bookPrice + "]";
		}
		

}
