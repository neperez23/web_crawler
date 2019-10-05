/*
 * Nicholas Perez
 * 11-21-16
 * SharedQueueLink.java
 * 
 * Stores a linked list of strings, each of which has a URL for a web document.
 */
package edu.greenriver.it.crawler;


import java.util.HashSet;
import java.util.LinkedList;


/**
 * @author Nicholas Perez
 * @version 1.0
 * 
 * Stores a linked list of strings, each of which has a URL for a web document.
 */
public class SharedQueueLink
{
	private static final int MAX_SIZE = 50000;
	private static LinkedList<String> linkQueue = new LinkedList<String>();
	private static HashSet<String> linkSets = new HashSet<String>();
	private static int count;
	
	
	private SharedQueueLink()
	{
		//does NOTHING BWAHAHAHAHAHAHHAHHHA /moarCACKLE!!!
	}

	/**
	 * Adds a link to the queue if it has not yet been seen. 
	 * This class should store a HashSet<String>of string URLs 
	 * that have been seen so far. You should use to HashSet object
	 *  to ensure that a link has not been added more than once.
	 * The queue should have a maximum size of 50000. If there is no
	 * room on the queue, then the thread should call wait() on the queue.
	 * After adding a new link to the queue, you should call notify() on the queue.
	 * 
	 * @param url adds a link to the queue
	 * @throws InterruptedException ?
	 */
	public static void addLink(String url) throws InterruptedException
	{
		synchronized(linkQueue)
		{
			while(linkQueue.size() >= MAX_SIZE)
			{
				linkQueue.wait();
			}//while
			
			if(linkSets.add(url))
			{
				linkQueue.add(url);
				count++;
				linkQueue.notify();
			}//if
			
		}//sync
	}//adlink
	
	/**
	 * Returns a link from the queue. If the queue is empty, 
	 * then the thread should call wait() on the queue.
	 * 
	 * After removing a link from the queue, you should call 
	 * notify() on the queue before returning the URL.
	 * 
	 * @return returns the next link in hte queue
	 * @throws InterruptedException ?
	 */
	public static String getNextLink() throws InterruptedException
	{
		synchronized(linkQueue)
		{
			while(linkQueue.size() <= 0)
			{
				linkQueue.wait();
			}//while
			
			String removed = linkQueue.removeFirst();
			linkQueue.notify();
			return removed;
		}//sync
	}//getnextlink
	
	/**
	 * This method returns the total number of unique links found so far through the use of the addLink() method.
	 * @return
	 */
	public static int getLinksFound()
	{
		synchronized(linkQueue)
		{
			return count;
		}//sync
	}//getlinksfound
	

}
