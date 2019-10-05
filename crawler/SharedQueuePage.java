/*
 * Nicholas Perez
 * 11-21-16
 * SharedQueuePage.java
 * 
 * Store a linked list of strings, each of which has the text of a web document.
 */
package edu.greenriver.it.crawler;

import java.util.LinkedList;


/**
 * @author Nicholas Perez
 * @version 1.0
 * Store a linked list of strings, each of which has the text of a web document.
 */
public class SharedQueuePage extends Thread
{
	private static final int MAX_SIZE = 50000;
	private static LinkedList<String> pageQueue = new LinkedList<String>();
	private static int count;
 

	private SharedQueuePage()
	{
		// does NOTHING BWAHAHAHAHAHAHHAHHHA /CACKLE!!!
	}

	/**
	 * Adds a new page to the queue. The queue should have a maximum size of 50000. 
	 * If there is no room on the queue, then the thread should call wait() on the queue.
	 * After adding a new page to the queue, you should call notify() on the queue.
	 * 
	 * @param pageText the html from of the page
	 * @throws InterruptedException ?
	 */
	public static void addPage(String pageText) throws InterruptedException
	{
		synchronized (pageQueue)
		{
			while (pageQueue.size() >= MAX_SIZE)
			{
				pageQueue.wait();
			}//while
			
			pageQueue.add(pageText);
			count ++;
			pageQueue.notify();
		}//sync
	}//addpage

	/**
	 * Returns a page from the queue. If the queue is empty, 
	 * then the thread should call wait() on the queue.
	 * 
	 * After removing a page from the queue, you should call notify() 
	 * on the queue before returning the page text.
	 * 
	 * @return returns the page gotten
	 * @throws InterruptedException ?
	 */
	public static String getNextPage() throws InterruptedException
	{
		synchronized (pageQueue)
		{
			while(pageQueue.size() <= 0)
			{
				pageQueue.wait();
			}//while
			
			String removed = pageQueue.removeFirst();
			pageQueue.notify();
			return removed;
		}//sync
	}//getnextpage

	/**
	 * This method returns the total number of pages that have been added to the queue through the addPage() method.
	 * @return returns count of page downloaded
	 */
	public static int getPagesDownloaded()
	{
		synchronized (pageQueue)
		{
			return count;
		}//sync
	}//getpagesdownloaded
}//class
