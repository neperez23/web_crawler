/*
 * Nicholas Perez
 * 11-21-16
 * Fetcher.java
 * 
 * A producer thread (Fetcher) should do the following repeatedly (ie. in an
 * infinite loop):
 * 
 * Pull a link from the link queue. Download the (HTML) page text at the given
 * URL. Store the (HTML) page text on the page queue as a string.
 */
package edu.greenriver.it.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Nicholas Perez
 * @version 1.0
 */
public class Fetcher extends Thread
{
	private int failCount;

	/*
	 * run() method for threads A producer thread (Fetcher) should do the
	 * following repeatedly (ie. in an infinite loop):
	 * 
	 * Pull a link from the link queue. Download the (HTML) page text at the
	 * given URL. Store the (HTML) page text on the page queue as a string.
	 */
	@Override
	public void run()
	{
		// a loop that pulls each line from the remote file over HTTP.
		while (true)
		{
			try
			{
				URL url = new URL(SharedQueueLink.getNextLink());

				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				BufferedReader download = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				String page = "";
				String line;

				while ((line = download.readLine()) != null)
				{
					page += line;
				} // while
				SharedQueuePage.addPage(page);
			} // try
			catch (InterruptedException | IOException e)
			{
				failCount++;
				SharedCounterData.addFailCount(failCount);
				failCount = 0;
				// System.out.println("U-dun fuked up... " + e.getMessage());
			} // catch

		} // while
	}// run
}// class
