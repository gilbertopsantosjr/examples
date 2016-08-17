package org.feed.rss.share.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "feed_provider")
public class FeedProvider {
	
	@Id
	private String id;
	private Long seq;
	private String nNumber;
	private String url;
	private String nickName;
	
	public FeedProvider(){
		super();
	}
	
	public FeedProvider(String _nNumber, String [] _urlAndNickName) {
		super();
		this.nNumber = _nNumber;
		this.url = _urlAndNickName[0];
		this.nickName = _urlAndNickName[1];
	}
	
	public FeedProvider( String _nNumber, String _url) {
		super();
		this.nNumber = _nNumber;
		this.url = _url;
	}
	
	public FeedProvider(String _nNumber, Long _seq) {
		super();
		this.nNumber = _nNumber;
		this.seq = _seq;
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getnNumber() {
		return nNumber;
	}
	public void setnNumber(String nNumber) {
		this.nNumber = nNumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FeedProvider other = (FeedProvider) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
