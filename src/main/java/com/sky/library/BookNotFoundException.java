package com.sky.library;

/*
 * Copyright © 2015 Sky plc All Rights reserved.
 * Please do not make your solution publicly available in any way e.g. post in forums or commit to GitHub.
 */

public class BookNotFoundException extends Exception
{
	public BookNotFoundException(final String message)
	{
		super(message);
	}
}
