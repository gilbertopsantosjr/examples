package com.gilbertosantos.model;


/**
 * @author Gilberto Santos 
 */
public class Snake  {
	
	private int itself;
	
	/** unique name */
	private String name;
	
	/** reference point to join onto other snake on head */
	private Snake head;
	
	/** reference point to join onto other snake on tail */
	private Snake tail;
	
	public Snake(String name) {
		super();
		this.name = name;
	}
	
	public Snake(String name, int itself) {
		this.name = name;
		this.itself = itself;
	}
	
	public int getItself() {
		return itself;
	}

	public void setItself(int itself) {
		this.itself = itself;
	}

	/**
	 * 
	 * @return singing 
	 */
	public String toSing(){
		return getName();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Snake getHead() {
		return head;
	}

	public void setHead(Snake head) {
		this.head = head;
	}

	public Snake getTail() {
		return tail;
	}

	public void setTail(Snake tail) {
		this.tail = tail;
		if(tail != null)
			tail.setHead(this);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Snake other = (Snake) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String stail = tail != null ? tail.getName() : " ";
		String shead = head != null ? head.getName() : " ";
		return "Snake [ name=" + name + ",  head=" + shead + ", tail=" + stail + "]";
	}
}
