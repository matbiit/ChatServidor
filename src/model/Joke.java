package model;

import java.util.ArrayList;
import java.util.List;

public class Joke {
	
	List<String> answers;
	private int answerIndex;
	
	public Joke(){
		this.answers = new ArrayList<String>();
	}
	
	public boolean isEnd() {
		return answers.size() == answerIndex;
	}

	public String tell(String msg) {
		String answer = null;
		System.out.println(answers.get(answerIndex));
		if(msg.equals("piada")){
			answer = answers.get(answerIndex++);
		}
		else if(msg.equals("quem é?"))
			answer = answers.get(answerIndex);
		else if(msg.equals(answers.get(answerIndex) + " quem?")){
			answer = answers.get(++answerIndex);
		}
		else{
			answer = "Não entendi porra nenhuma.";
		}
		System.out.println(answerIndex);
		return answer;
			
	}

}
