package model;

import java.util.ArrayList;
import java.util.List;

public class KnocKnocJoke {
	
	List<String> answers;
	private int answerIndex;
	private boolean isEnd;
	
	public KnocKnocJoke(){
		this.answers = new ArrayList<String>();
		this.answers.add("Knoc knoc");
	}
	
	public boolean isEnd() {
		return this.isEnd;
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
			this.isEnd = true;
		}
		else{
			answer = "Não entendi porra nenhuma.";
		}
		System.out.println(answerIndex);
		return answer;
			
	}

}
