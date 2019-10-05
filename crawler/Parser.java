/*
 * Nicholas Perez
 * 11-21-16
 * Parser.java
 * 
 * A consumer thread (Parser) should do the following repeatedly (ie. in an infinite loop):
 * 
 * pull a page from the page queue
 * search the page for all links in anchor (<a href="">) elements
 * add each link found to the link queue
 * search the page for any keywords specified by the user of the web crawler (more on this later)
 * keep track of how many keywords are encountered
 */

package edu.greenriver.it.crawler;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Nicholas Perez
 * @version 1.0
 * 
 * A consumer thread (Parser) should do the following repeatedly (ie. in an infinite loop):
 * 
 * pull a page from the page queue search the page for all links in
 * anchor (<a href="">) elements add each link found to the link queue
 * search the page for any keywords specified by the user of the web
 * crawler (more on this later) keep track of how many keywords are encountered
 */
public class Parser extends Thread
{
	private int keyCount;

	/**
	 * run() for the thread
	 */
	@Override
	public void run()
	{
		while (true)
		{
			ArrayList<String> keywords = SharedCounterData.getKeyword();

			try
			{
				String page = SharedQueuePage.getNextPage();

				Pattern pattern = Pattern.compile("href=\"(http:.*?)\"");
				Matcher matcher = pattern.matcher(page);

				// search the page for all links in anchor (<a href="">)
				// elements
				// and add each link found to the link queue
				while (matcher.find())
				{
					String link = matcher.group(1);

					SharedQueueLink.addLink(link);
				} // while

				// search the page for any keywords specified by the user of the
				// web crawler (more on this later)
				// keep track of how many keywords are encountered
				for (int i = 0; i < keywords.size(); i++)
				{
					String[] parts = page.split(keywords.get(i));
					keyCount += parts.length - 1;
				} // for

				SharedCounterData.addKeywordCount(keyCount);
				keyCount = 0;

			} // try
			catch (InterruptedException e)
			{
				System.out.println("Lots of stuff went wrong... " + e.getMessage());
			} // catch
		} // while
	}// run
}// class
