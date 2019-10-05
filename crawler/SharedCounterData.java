/*
 * Nicholas Perez
 * 11-21-16
 * SharedCounterData.java
 * 
 * Shared data class to handle the counts for all the threads while being thread safe...
 * 
 */
package edu.greenriver.it.crawler;

import java.util.ArrayList;

/**
 * 
 * @author Nicholas
 * @version 1.0
 * 
 * Shared data class to handle the counts for all the threads while being thread safe...
 */
public class SharedCounterData
{
	private static ArrayList<String> keywords = new ArrayList<String>();
	private static int keywordCount;
	private static int failCount;
	

	private SharedCounterData()
	{
		//like i said .. does nothing .. XP
	}

	/**
	 *  adds a keyword to the array list
	 * @param word the keyword
	 */
	public static void addKeyWord(String word)
	{
		keywords.add(word);
	}//addkeyword
	
	/**
	 * gets the arraylist
	 * @return the arraylist
	 */
	public static ArrayList<String> getKeyword()
	{
		return keywords;
	}//getkeyword arraylist
	
	/**
	 * gets the keyword size
	 * @return the size
	 */
	public static int getKeywordSize()
	{
		return keywords.size();
	}//getkeywordsize

	/**
	 * gets the keyword count
	 * @return the keyword count
	 */
	public static int getKeywordCount()
	{
		return keywordCount;
	}//getkeywordcount

	/**
	 *  adds to the keyword count
	 * @param count count number
	 */
	public static void addKeywordCount(int count)
	{	
		synchronized(keywords)
		{
			keywordCount += count;
		}//sync
	}//addkeywordcount
	
	/**
	 * adds to the fail count
	 * @param count is the fail count
	 */
	public static void addFailCount(int count)
	{
		synchronized(keywords)
		{
			failCount += count;
		}//sync
	}//addfailcount
	
	/**
	 * gets the fail count
	 * @return the fail count
	 */
	public static int getFailCount()
	{
		return failCount;
	}//getfailcount
	
}
