package com.gilbertosantos.model;

import java.util.HashSet;
import java.util.Set;

public class SnakeEnsemble {
	
	private Snake current = null;
	private Set<Snake> ensemble = new HashSet<>();
	private int total = 0;
	
	public void add(String name){
		if(total == 0){
			Snake snake = new Snake(name);
			snake.setHead(snake);
			snake.setTail(snake);
			this.current = snake;
		} else {
			Snake snake = new Snake(name);
			snake.setHead(null);
			snake.setTail(current);
			
			this.current = snake;
		}
		total++;
	}
	
	public void add(String name, int itself){
		ensemble.add(new Snake(name, itself));
	}
	
	public Snake find(int position){
		for (Snake snake : ensemble) {
			if(snake.getItself() == position)
				return snake;
		}
		return null;
	}
	
	public Set<Snake> getEnsemble() {
		return ensemble;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(" ");
		Snake get = current;
		if(get != null){
			// inverting list
			for (int i = 0; i < total; i++) {
				get = get.getTail();
			}
			
			// getting the next till head be null
			while(get.getHead() != null){
				sb.append(get);
				get = get.getHead();
			}
		}
		return sb.toString(); 
	}
	
}
