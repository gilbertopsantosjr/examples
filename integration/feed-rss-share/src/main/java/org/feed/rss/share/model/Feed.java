package org.feed.rss.share.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "feed")
public class Feed {

	@Id
	private String id;
	
	private String title;
	
	private String link;
	
	private Date publishDate;
	
	public Feed(){
		super();
	}

	public Feed(Date publishedDate, String title, String link) {
		super();
		this.publishDate = publishedDate;
		this.title = title;
		this.link = link;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "Feed [id=" + id + ", title=" + title + ", link=" + link + "]";
	}

	public static Feed newFeed(Date publishedDate, String title, String link) {
		return new Feed(publishedDate, title, link);
	}
	
	
	
	
}
