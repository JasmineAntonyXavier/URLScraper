package com.urlscraper;

public class ScraperMain {
	public static void main (String args[]) {

		if (args.length < 1){
			System.out.println("Please pass in a URL as first argument");
		}else if (args.length > 1){
			System.out.println("Please pass only one argument");
		}else{
			Scraper scrape = new Scraper();
			scrape.scrapeUrls(args[0]);
		}
	}
}
