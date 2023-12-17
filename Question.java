
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//Provides attributes and functionalities for each Question object
public class Question {
	
	//The question itself
	private String questionText;
	//The correct answer to that question
	private String correctAnswer;
	//The question category
	private String category;
	//All the wrong answers that can be given for the question
	private String[] wrongAnswers;
	/*The alternatives a, b, c or d that can be selected and their associated answers that can be
	correct or wrong*/
	private Map<Character, String> alternatives;
	
	
	
	//Class constructor that uses the setters to assign values to the class attributes
	public Question(String questionText, String correctAnswer, String category, String[] wrongAnswers) {
		super();
		this.setQuestionText(questionText);
		this.setCorrectAnswer(correctAnswer);
		this.setCategory(category);
		this.setWrongAnswers(wrongAnswers);
		this.setAlternatives();
	}
	
	
	
	
	//Returns the question itself
	public String getQuestionText() {
		return questionText;
	}
	//Sets the question if the argument is not an empty String
	public void setQuestionText(String questionText) {
		if(questionText!="")
		this.questionText = questionText;
		else
		{
			//Otherwise it prints an error message and stops the whole program execution
			System.out.println("No question was read from the file.");
			System.exit(1);
		}
	}
	
	//Returns the correct answer to the question
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	
	//Sets the correct answer if the argument is not an empty String
	public void setCorrectAnswer(String correctAnswer) {
		if(correctAnswer!="")
		this.correctAnswer = correctAnswer;
		else
		{
			//Otherwise it prints an error message and stops the whole program execution
			System.out.println("No correct answer to question '"+this.questionText+
					"' was read from the file.");
			System.exit(1);
		}
	}
	
	//Returns the question category
	public String getCategory() {
		return category;
	}
	
	//Sets the category if the argument is not an empty String
	public void setCategory(String category) {
		if(category!="")
		this.category = category;
		else
		{
			//Otherwise it prints an error message and stops the whole program execution
			System.out.println("No category to question '"+this.questionText+
					"' was read from the file.");
			System.exit(1);
		}
	}
	
	//Returns all the wrong answers to the question
	public String[] getWrongAnswers() {
		return wrongAnswers;
	}
	
	//Sets the wrong answers if the parameters are not empty Strings and come in an array of 3 elements
	public void setWrongAnswers(String[] wrongAnswers) {
		boolean containsEmptyStrings=false;
		for(String element: wrongAnswers)
		{
			if(element.equals(""))
			{
				containsEmptyStrings=true;
				break;
			}
		}
		
		//Checks if the String array passed as method parameter contains 3 Strings, none of which are empty
		if(wrongAnswers.length==3 && containsEmptyStrings==false)
		this.wrongAnswers = wrongAnswers;
		else
		{
			//Otherwise it prints an error message and stops the whole program execution
			System.out.println("Not enough wrong answers to question '"+this.questionText+
					"' were read from the file.");
			System.exit(1);
		}
	}
	
	
	
	
	
	//Returns the question alternatives
	public Map<Character, String> getAlternatives() {
		return alternatives;
	}




	//Sets to the alternatives A, B, C and D a random answer to the question
	public void setAlternatives() {
		this.alternatives = new HashMap<>();
		
		//Declares a String ArrayList to store and manipulate all question answers
		List<String> answers= new ArrayList<String>();
		//Adds the correct answer to the List
		answers.add(this.correctAnswer);
		//Adds all the wrong answers to the list
		answers.addAll(Arrays.asList(this.wrongAnswers));
		//Randomly reorders the elements in the list
		Collections.shuffle(answers);
		
		//Assigns the List answers reordered randomly to the alternatives A, B, C and D
		alternatives.put('A', answers.get(0));
		alternatives.put('B', answers.get(1));
		alternatives.put('C', answers.get(2));
		alternatives.put('D', answers.get(3));
	}




	//Checks if the user/player's answer is the same as the correct answer
	public boolean checkAnswer(String userAnswer)
	{
		if(this.correctAnswer.equals(userAnswer))
			return true;
		else
			return false;
	}
	
	//Displays the question, handles the answer alternative input by the user and checks if it's correct
	public static boolean displayQuestion(Question q, int questionNumber)
	{
		System.out.println();
		System.out.println("Question "+questionNumber+":");
		System.out.println(q.questionText);
	
		//Displays all the alternatives and their associated answers to the question
		for(Map.Entry<Character, String> entry : q.alternatives.entrySet())
		{
			System.out.println(entry.getKey()+" -> "+entry.getValue());
		}
		
		Scanner scanner= new Scanner(System.in);
		
		char alternative;
		
		while(true) 
		{
			System.out.println();
			 try {
				 System.out.print("Enter the alternative that you think is correct. Enter A, B, C or D: ");
				 String userInput = scanner.next();
				 
				 /*If the user input is a String, but it has more than one character, an
				 exception is thrown*/
				 if(userInput.length()>1)
					 throw new IllegalArgumentException();
				 
                alternative = userInput.toUpperCase().charAt(0);
                
                /*If the alternative input by the user is not between A and D, an exception is 
                 thrown*/
                if (alternative < 'A' || alternative > 'D') {
                    throw new IllegalArgumentException(); // Throws exception for invalid input
                }
                break; // Breaks out of the loop if input is valid
            } catch (Exception e) {
            	/*Handles the exception by printing an error message prompting the user to enter a 
            	 valid input during the next loop iteration*/
                System.out.println("Invalid input! Please enter A, B, C, or D.");
            }
        }
		
		//Gets the answer text from the alternatives HashMap
		String answer= q.alternatives.get(alternative);
		
		//Checks if the answer is the same as the correct Answer
		if(answer.equals(q.correctAnswer))
			return true;
		else
			return false;

		
	}
	
	
	
	
}

