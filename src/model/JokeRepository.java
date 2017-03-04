package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JokeRepository {
	
	private List<KnocKnocJoke> jokes;
	private Random randomGenerator;
	
	public JokeRepository(){
		this.randomGenerator = new Random();
		this.jokes = new ArrayList<KnocKnocJoke>();
		
		KnocKnocJoke joke = new KnocKnocJoke();
		joke.answers.add("Sunda");
		joke.answers.add("aquele que comeu seu bolo.");
		this.jokes.add(joke);
		
		KnocKnocJoke joke2 = new KnocKnocJoke();
		joke2.answers.add("Bond");
		joke2.answers.add("James bond! xD");
		this.jokes.add(joke2);
		
		KnocKnocJoke joke3 = new KnocKnocJoke();
		joke3.answers.add("Uma");
		joke3.answers.add("Uma formiga HAHA");
		this.jokes.add(joke3);
		
		KnocKnocJoke joke4 = new KnocKnocJoke();
		joke4.answers.add("Ota");
		joke4.answers.add("ota formiga! HEHE ");
		this.jokes.add(joke4);
		
		KnocKnocJoke joke5 = new KnocKnocJoke();
		joke5.answers.add("Lula");
		joke5.answers.add("Lula preso amanhã.");
		this.jokes.add(joke5);
		
		KnocKnocJoke joke6 = new KnocKnocJoke();
		joke6.answers.add("Eduardo");
		joke6.answers.add("Eduardo cunha - Como vota deputado?! ");
		this.jokes.add(joke6);
		
		KnocKnocJoke joke7 = new KnocKnocJoke();
		joke7.answers.add("Rouba mas faz");
		joke7.answers.add("Maluf hehe.");
		this.jokes.add(joke7);
		
		
	}

	public KnocKnocJoke getJoke() {
		int randomIndex = randomGenerator.nextInt(jokes.size());
		return jokes.get(randomIndex); 
	}
}
