/*
 * Nicholas Perez
 * 11-21-16
 * Driver.java
 * 
 * Driver class for a simple web crawler. 
 * The purpose of a web crawler is to traverse the links on 
 * a network (in this case the Internet) and collect statistics
 * on each page encountered.
 */
package edu.greenriver.it.crawler;

import java.util.ArrayList;

import edu.greenriver.it.console.Console;

/**
 * 
 * @author Nicholas Perez
 * @version 1.0
 * 
 * Driver class for a simple web crawler. 
 * The purpose of a web crawler is to traverse the links on 
 * a network (in this case the Internet) and collect statistics
 * on each page encountered.
 */
public class Driver
{
	private static ArrayList<Thread> fetchers = new ArrayList<Thread>();
	private static ArrayList<Thread> parsers = new ArrayList<Thread>();

	/**
	 * Choosing this menu option will print out the following details of the web crawler:
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException
	{
		
		
		main_loop: while(true)
		{
			menu();
			
			switch(Console.getInt("Enter a choice"))
			{
				case 1:
					SharedQueueLink.addLink(Console.getString("Enter a seed URL"));
					break;
				case 2:
					
					parsers.add(new Parser());
					parsers.get(parsers.size()-1).start();
					break;
				case 3:
					fetchers.add(new Fetcher());
					fetchers.get(fetchers.size()-1).start();
					break;
				case 4:
					SharedCounterData.addKeyWord(Console.getString("Enter keyword"));
					break;
				case 5:
					System.out.println("Keywords Found: "+ SharedCounterData.getKeywordCount());
					System.out.println("Links Found: "+SharedQueueLink.getLinksFound());
					System.out.println("Pages Found: "+SharedQueuePage.getPagesDownloaded());
					System.out.println("Failed Downloads: "+SharedCounterData.getFailCount());
					System.out.println("Producers: "+fetchers.size());
					System.out.println("Consumers: "+parsers.size());
					break;
				case 6:
					break main_loop;
			}//switch
		}//while
	}//main
	
	//menu options
	private static void menu()
	{
		System.out.println("1. Add seed url");
		System.out.println("2. Add consumer");
		System.out.println("3. Add producer");
		System.out.println("4. Add keyword search");
		System.out.println("5. Print stats");
		System.out.println("6. Exit");
		
	}//menu

}
