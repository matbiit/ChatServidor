package model;

import java.util.ArrayList;
import java.util.List;

public class JokeRepository {
	
	private List<Joke> jokes;
	
	public JokeRepository(){
		this.jokes = new ArrayList<Joke>();
		Joke joke = new Joke();
		joke.answers.add("Knoc knoc");
		joke.answers.add("Sunda");
		joke.answers.add("aquele que comeu sua bunda!");
		this.jokes.add(joke);
	}

	public Joke getJoke() {
		System.out.println("Terminou!");
		return jokes.get(0); 
	}
}
