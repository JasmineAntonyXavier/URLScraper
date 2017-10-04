package com.urlscraper;

import org.apache.commons.validator.UrlValidator;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.util.HashSet;

public class Scraper {

	private HashSet<String> allLinks = new HashSet<String>();
	private int count = 0;

	public void scraper(String link) {

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

		Elements links = doc.select("a[href]");

		for (int i = 0; i < links.size(); i++) {
			String linkhref = links.get(i).attr("href");
		
			if (count >= 50){
				return;
			}else if (allLinks.contains(linkhref)) {
				continue;
			} else {
				allLinks.add(linkhref);
				System.out.println("Link #"+(count+1)+": " + linkhref);
				count++;
				scraper(linkhref);
			}
		}
	}
}