package com.gilbertosantos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gilbertosantos.model.Snake;
import com.gilbertosantos.model.SnakeEnsemble;


/**
 * @author Gilberto Santos 
 */
public class Functions {
	
	private String filename;
	private SnakeEnsemble snakes = new SnakeEnsemble();
	private Set<Snake> all = new HashSet<>();
	private final Comparator<Snake> byItself = (s1, s2) -> Integer.compare(s1.getItself(), s2.getItself());
	
	public Functions(String filename){
		this.filename = filename;
	}
	
	public void createSnakes() throws Exception {
		List<String[]> list = getAllSnakesFromFile();
		int cont = 0;
		for (String[] s : list) {
			snakes.add(s[0], cont);
			cont++;
		}
		organizeSnakes();
	}

	private void organizeSnakes() throws Exception {	
		List<String[]> rows = getAllSnakesFromFile();
		List<Snake> clones = new ArrayList<>(snakes.getEnsemble());
		Collections.sort(clones, byItself);
		for (Snake snake : clones) {
			//clone
			Snake clone = new Snake(snake.getName(), snake.getItself());
			for (String[] args : rows) {
				int head = Integer.valueOf(args[1].trim()).intValue();
				int tail = Integer.valueOf(args[2].trim()).intValue();
				clone.setHead(snakes.find(head));
				clone.setTail(snakes.find(tail));
				// pop
				rows.remove( rows.get(0) );
				break;
			}
			all.add(clone);
		}
	}
	
	public Set<Snake> getAll() {
		return all;
	}

	private List<String[]> getAllSnakesFromFile() throws Exception{
		List<String[]> list = new ArrayList<>();
		BufferedReader br = null;
		try {
			String line = "";
			InputStream in = ClassLoader.getSystemResourceAsStream(this.filename);
			br = new BufferedReader(new InputStreamReader(in));
			
			while ((line = br.readLine()) != null) {
				String[] snakeRow = line.split(",");
				list.add(snakeRow);
			}

		} catch (FileNotFoundException e) {
			throw e;

		} catch (IOException e) {
			throw e;

		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	} 

}
