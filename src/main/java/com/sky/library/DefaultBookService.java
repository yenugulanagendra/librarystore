package com.sky.library;

import java.util.StringTokenizer;

public class DefaultBookService implements BookService
{
	private static final String ELLIPIS = "...";
	private static final String PREFIX = "BOOK-";
	private static final int SPLIT_WORDS = 9;

	private BookRepository bookRepository;

	@Override
	public Book retrieveBook(final String bookReference) throws BookNotFoundException
	{

		checkBookReference(bookReference);

		final Book book = bookRepository.retrieveBook(bookReference);
		if (book == null)
		{
			throw new BookNotFoundException("Book with reference " + bookReference + " not found");
		}
		return book;

	}

	@Override
	public String getBookSummary(final String bookReference) throws BookNotFoundException
	{
		checkBookReference(bookReference);
		final Book book = bookRepository.retrieveBook(bookReference);

		if (book == null)
		{
			throw new BookNotFoundException("Book with reference " + bookReference + " not found");
		}

		StringBuffer builder = new StringBuffer();
		builder.append("[").append(bookReference).append("]").append(" ");
		builder.append(book.getTitle()).append(" - ");
		builder.append(getBookReview(book.getReview()));
		return builder.toString();

	}

	private void checkBookReference(final String bookReference) throws BookNotFoundException
	{
		if (bookReference == null)
		{
			throw new BookNotFoundException("The given bookReference must not be null!");
		}

		if (!bookReference.startsWith(PREFIX))
		{
			throw new BookNotFoundException("The given bookReference must begin with BOOK-");
		}
	}

	private String getBookReview(String review)
	{
		if (countWordsInReview(review) > 9)
		{
			StringBuffer builder = new StringBuffer();
			int counter = 1;
			StringTokenizer tokens = new StringTokenizer(review.replaceAll(",", ""));
			while (tokens.hasMoreTokens() && counter <= SPLIT_WORDS)
			{
				builder.append(tokens.nextToken());

				if (counter < SPLIT_WORDS)
					builder.append(" ");

				counter++;
			}
			builder.append(ELLIPIS);

			return builder.toString();

		}
		return review;
	}

	private int countWordsInReview(String review)
	{
		if (review == null || review.length() == 0)
		{
			return 0;
		}

		StringTokenizer tokens = new StringTokenizer(review);
		return tokens.countTokens();
	}

	public void setBookRepository(final BookRepository bookRepository)
	{
		this.bookRepository = bookRepository;
	}

}
