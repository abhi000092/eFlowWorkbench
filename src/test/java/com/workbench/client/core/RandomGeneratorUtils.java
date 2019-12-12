package com.workbench.client.core;

import java.util.Random;

public class RandomGeneratorUtils 
{
	private static Random random = new Random();
	private static StringBuilder randomValueHolder = new StringBuilder();
	private static String userName = "123QWERTYUIOPASDFGABCDEFGHIJKLMNO456hjklzxcvbnmabcdefghijklmno789";
	private static String password = "123QWERTYUIOPASDFG456hjklzxcvbnm789~!@#$%^&*_+-=|(){}<>?/,.";

	public static int getRandomNumber(int upperRange)
	{
		return random.nextInt(upperRange);
	}

	public static String getRandomString(String sourceString, int length)
	{
		for(int i = 0; i < length; i++)
		{
			randomValueHolder.append(sourceString.charAt(random.nextInt(sourceString.length())));
		}
		return randomValueHolder.toString();
	}

	public static String generateRandomUserId()
	{
		return "Newuser"+ getRandomString(userName, 6) ;
	}
	
	public static String generateRandomPassword()
	{
		return getRandomString(password, 6);
	}
	
}
