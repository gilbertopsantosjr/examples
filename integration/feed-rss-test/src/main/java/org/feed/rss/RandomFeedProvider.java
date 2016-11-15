package org.feed.rss;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class RandomFeedProvider {
	
	final static Random r = new Random();
	
	public static String [] getOneFeed() {
		final List<String[]> feeds = new ArrayList<String[]>();
		for (SomeFeeds s : SomeFeeds.values()) 
			feeds.add(new String[] {s.getLabel(), s.getNickName()});	
		return feeds.get(r.nextInt(feeds.size()));
	}
	
	public static String getNNumber(){
		final List<String> nNumbers = new ArrayList<String>();
		for (NNumberUsers s : NNumberUsers.values()) 
			nNumbers.add(s.getLabel());	
		return nNumbers.get(r.nextInt(nNumbers.size()));
	}
}

enum NNumberUsers{

	GILBERTO_SANTOS("****"),
	SIMON_COWAN("****"),
	GILLIAN_ARMSTRONG("****");
	
	private String label;
	
	NNumberUsers(String _label){
		this.label = _label;
	}
	public String getLabel(){
		return label;
	}
}

enum SomeFeeds {
	
	SAMPLE_01("http://www.businessinsurance.com/section/rss?tagID=52", "business insurance"), 
	SAMPLE_02("http://www.businessinsurance.com/section/rss?feed=NEWS", "business insurance news"), 
	SAMPLE_03("http://www.businessinsurance.com/section/rss?feed=NEWS04", "business insurance news 4"),
	SAMPLE_04("http://www.businessinsurance.com/section/rss?feed=NEWS08", "business insurance news 8"), 
	SAMPLE_05("http://www.propertycasualty360.com/Claims?f=rss", "property casualty 360 claims"), 
	SAMPLE_06("http://www.propertycasualty360.com/Agent-Broker?f=rss", "property casualty 360 agent broker"), 
	SAMPLE_07("http://www.einsurance.com/rss/Specialty-Insurance.aspx", "Specialty Insurance"), 
	SAMPLE_08("http://www.einsurance.com/rss/Auto-Insurance.aspx", "Auto Insurance"), 
	SAMPLE_09("http://www.einsurance.com/rss/Business-Insurance.aspx", "Business Insurance"), 
	SAMPLE_10("http://www.einsurance.com/rss/Home-Insurance.aspx", "Home Insurance"), 
	SAMPLE_11("http://www.einsurance.com/rss/Life-Insurance.aspx", "Life Insurance"), 
	SAMPLE_12("http://kaiserhealthnews.org/topics/insurance/feed/", "kaiserhealthnews"), 
	SAMPLE_13("http://www.insuranceheadlines.com/rss/rss.php?wcCategory=0","insuranceheadlines category 0"), 
	SAMPLE_14("http://www.insuranceheadlines.com/rss/rss.php?wcCategory=2","insuranceheadlines category 1"), 
	SAMPLE_15("http://www.insuranceheadlines.com/rss/rss.php?wcCategory=6","insuranceheadlines category 6"), 
	SAMPLE_16("http://www.insurancejournal.com/rss/news/", "insurance journal");

	private String label;
	private String nickName;

	SomeFeeds(String _label, String _nickName) {
		this.label = _label;
		this.nickName = _nickName;
	}

	public String getLabel() {
		return label;
	}

	public String getNickName() {
		return nickName;
	}
	
	
}
