package com.sky.library;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class BookServiceTest
{

	private static final String INVALID_TEXT = "INVALID-TEXT";
	private static final String BOOK_GRRUFFALO = "BOOK-GRUFF472";
	private static final String BOOK_999 = "BOOK-999";
	private static final String BOOK_WILL987 = "BOOK-WILL987";

	private BookService bookService;

	private BookRepository bookRepository;

	@Before
	public void setUp()
	{
		bookRepository = new BookRepositoryStub();
		bookService = new DefaultBookService();
		((DefaultBookService)bookService).setBookRepository(bookRepository);
	}

	@Test(expected = BookNotFoundException.class)
	public void when_retrieve_book_is_null() throws BookNotFoundException
	{
		bookService.retrieveBook(null);
	}

	@Test(expected = BookNotFoundException.class)
	public void testInvalidText() throws BookNotFoundException
	{
		bookService.retrieveBook(INVALID_TEXT);
	}

	@Test(expected = BookNotFoundException.class)
	public void testInvalidBook() throws BookNotFoundException
	{
		bookService.retrieveBook(BOOK_999);
	}

	@Test
	public void testWithValidBookId() throws BookNotFoundException
	{
		Book book = bookService.retrieveBook(BOOK_GRRUFFALO);
		assertEquals("Name of book by reference", book.getTitle(), "The Gruffalo");
	}

	@Test(expected = BookNotFoundException.class)
	public void testInvalidTextForBookSummary() throws BookNotFoundException
	{
		bookService.getBookSummary(INVALID_TEXT);
	}

	@Test(expected = BookNotFoundException.class)
	public void testInvalidBookForBookSummary() throws BookNotFoundException
	{
		bookService.getBookSummary(BOOK_999);
	}

	@Test
	public void testBookSummary() throws BookNotFoundException
	{
		final String bookSummary = bookService.getBookSummary(BOOK_GRRUFFALO);
		assertEquals("Book summary ", bookSummary, "[BOOK-GRUFF472] The Gruffalo - A mouse taking a walk in the woods");
	}

	@Test
	public void testBookSummaryWithMoreWords() throws BookNotFoundException
	{
		final String bookSummary = bookService.getBookSummary(BOOK_WILL987);
		assertEquals("Book summary ", bookSummary, "[BOOK-WILL987] The Wind In The Willows - With the arrival of spring and fine weather outside...");

	}
}
