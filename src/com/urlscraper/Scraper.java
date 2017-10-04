package com.urlscraper;

import org.apache.commons.validator.UrlValidator;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.util.HashSet;

public class Scraper {

	//List of All Links
	private HashSet<String> allLinks = new HashSet<String>();
	
	//Number of links that have been traversed
	private int count = 0;

	public void scrapeUrls(String link) {

		//Download the website into a document using Jsoup; Use UrlValidator to see if URL is valid
		Document doc = null;
		try {
			UrlValidator urlVal = new UrlValidator();
			if (urlVal.isValid(link)) {				
				doc = Jsoup.connect(link).get();
			} else {
				return;
			}
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}

		//Get all links that are in the Page
		Elements links = doc.select("a[href]");

		//Get the URL that was embedded in the href tag
		for (int i = 0; i < links.size(); i++) {
			String linkhref = links.get(i).attr("href");
		
			//If Count > 50 then stop or if the link has already been traversed then go to next link
			if (count >= 50){
				return;
			}
			else if (allLinks.contains(linkhref)) {
				continue;
			} else {
				
				//Add the link to the hashset
				allLinks.add(linkhref);
				
				//Output the link that was found
				System.out.println("Link #"+(count+1)+": " + linkhref);
				
				//Increment the count
				count++;
				
				//Scrape the URL of the next link that was found
				scrapeUrls(linkhref);
			}
		}
	}
}