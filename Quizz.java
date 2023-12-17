
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


//Provides attributes and functionalities for each Quizz game/object
public class Quizz {
	
	//The quizz questions
	private List<Question> questionsList;
	//The money the user has earned up to a specific moment during the quizz
	private int userScore;
	//The extra amount of money the user can earn if he/she answers another question
	private int reward;
	//The geography questions contained in the file
	private List<Question> geographyQuestions;
	//The history questions contained in the file
	private List<Question> historyQuestions;
	//The football questions contained in the file
	private List<Question> footballQuestions;
	//The Java questions contained in the file
	private List<Question> javaQuestions;
	//The main categories that a question can belong to
	private List<String> categories;
	//The number of the current quizz question
	private int questionCounter;
	
	
	//Class constructor
	public Quizz()
	{
		this.questionsList= new ArrayList<Question>();
		this.userScore=0;
		this.geographyQuestions= new ArrayList<Question>();
		this.historyQuestions= new ArrayList<Question>();
		this.footballQuestions= new ArrayList<Question>();
		this.javaQuestions= new ArrayList<Question>();
		this.categories=new ArrayList<String>();
		this.questionCounter=1;
		this.reward=25000;
		
		this.categories.add("Geography");
		this.categories.add("History");
		this.categories.add("Football");
		this.categories.add("Java");
	}


	//Returns the final list of quizz questions
	public List<Question> getQuestionsList() {
		return questionsList;
	}


	//Returns the money the user has won during the quizz
	public int getUserScore() {
		return userScore;
	}


	//Returns the geography questions that are inside the file
	public List<Question> getGeographyQuestions() {
		return geographyQuestions;
	}


	//Returns the history questions that are inside the file
	public List<Question> getHistoryQuestions() {
		return historyQuestions;
	}


	//Returns the football questions that are inside the file
	public List<Question> getFootballQuestions() {
		return footballQuestions;
	}


	//Returns the java questions that are inside the file
	public List<Question> getJavaQuestions() {
		return javaQuestions;
	}


	//Returns the 4 question categories
	public List<String> getCategories() {
		return categories;
	}
	
	
	//Returns the money reward for answering another question correctly
	public int getReward() {
		return reward;
	}


	//Returns the number of questions asked during the quizz
	public int getQuestionCounter() {
		return questionCounter;
	}


	
	


	//Reads all the questions inside the question file
	public void deserializeQuestionsFromCSV(String fileName)
	{
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            //Reads the header which contains all the Question class attributes
            String headerLine = br.readLine();
            
            //Splits and stores all header words inside a String array
            String[] headers = headerLine.split(",");

            //Reads the data lines
            String line;
            //Keeps reading until there is no more information in the file
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                //Checks if the number of values is the same as the number of attributes
                if (values.length == headers.length) {
                	
                	//Stores the values
                	String questionText= values[0];
                	String correctAnswer= values[1];
                	String category=values[2];
                	String[] wrongAnswers= {values[3], values[4], values[5]};
                	
                	//Creates a Question object using the values
                	Question q= new Question(questionText, correctAnswer, category, wrongAnswers);
                	
                	//Checks if the question category contains one of the 4 quizz question categories
                	if(category.toLowerCase().contains("geography"))
                		this.geographyQuestions.add(q);
                	else if(category.toLowerCase().contains("history"))
                		this.historyQuestions.add(q);
                	else if(category.toLowerCase().contains("football"))
                		this.footballQuestions.add(q);
                	else if(category.toLowerCase().contains("java"))
                		this.javaQuestions.add(q);
                	else
                	{
                		//Otherwise, an error message is printed and program execution ends
                		System.out.println("The category of question '"+questionText+"' is not one of the 4 categories of quizz questions.");
                		System.exit(1);
                	}
                	
                }
            }
        } catch (IOException e) {
        	//If an Exception is caught, its information is printed
            e.printStackTrace();
        }
	}
	
	
	//Prints out the quizz rules
	public void displayQuizzRules()
	{
		System.out.println("Welcome to the quizz!");
		System.out.println();
		System.out.println("You will have to answer 4 questions, each from a different category to win.");
		System.out.println("If you answer the first question correctly, you win $25000.");
		System.out.println("For each extra question that you answer, the reward doubles ");
		System.out.println("($50000 more for the second question, $100000 more for the third and $200000 more for the fourth question).");
		System.out.println("If you want to walk away after answering a question correctly, you keep the money you have earned up to that point.");
		System.out.println("If you move on to the next question and you answer correctly, you earn more money.");
		System.out.println("If you answer the next question incorrectly though, you lose all the money you have earned up to that point.");
		System.out.println();
		System.out.println("Good luck!");
		System.out.println();
		System.out.println();
	}
	
	//Begins the quizz game and handles everything that happens during game duration
	public void startQuizz()
	{
		//The questions are read from the file
		this.deserializeQuestionsFromCSV("Questions.csv");
		
		//The questions for each category are randomly ordered
		Collections.shuffle(historyQuestions);
		Collections.shuffle(footballQuestions);
		Collections.shuffle(geographyQuestions);
		Collections.shuffle(javaQuestions);
		
		//4 questions are selected for the quizz, 1 from each category
		this.questionsList.add(this.historyQuestions.get(0));
		this.questionsList.add(this.footballQuestions.get(0));
		this.questionsList.add(this.geographyQuestions.get(0));
		this.questionsList.add(this.javaQuestions.get(0));
		
		displayQuizzRules();
		
		while(true)
		{
			//Checks if there are any remaining questions unanswered by the player
			if(this.questionsList.isEmpty()==false)
			{
				System.out.println();
				System.out.println("Choose a category from these categories below:");
				System.out.println();
				Scanner scanner= new Scanner(System.in);
				
				//Displays all categories and the number the user has to enter to select each of them
				for(int i=0; i<this.questionsList.size(); i++)
				{
					int displayedNumber=i+1;
					System.out.println("Enter "+displayedNumber+" for "+this.questionsList.get(i).getCategory()+".");
				}
				//Declares and initializes a variable to store the number the user will enter
				int questionNumber=-1;
				
				try {
					questionNumber=scanner.nextInt();
					//Checks if the number entered by the user is corresponding to one of the categories
					if(questionNumber<1 || questionNumber>this.questionsList.size())
					{
						throw new IllegalArgumentException();
					}
					
					/*Makes the questionNumber smaller by 1 to make it correspond to the question's index 
					 inside the list of quizz questions*/
					questionNumber--;
					
					//Displays the question and evaluates the user answer to it
					Question q= this.questionsList.get(questionNumber);
					boolean correctAnswer=Question.displayQuestion(q, this.questionCounter);
					
					//Increments the number of quizz questions about to be answered
					this.questionCounter++;
					
					
					System.out.println();
					//Checks if the user's answer is wrong
					if(!correctAnswer)
					{
						//The money the user has won during the quizz becomes $0
						this.userScore=0;
						System.out.println("Incorrect answer.");
						System.out.println("The correct answer was "+q.getCorrectAnswer());
						System.out.println("The quizz has ended. You did not win any money.");
						break;
					}else
					{
						//The money the user has won during the quizz gets incremented by the question reward amount
						this.userScore+=this.reward;
						//The reward doubles for the next question 
						this.reward=this.reward*2;
						
						System.out.println("Your answer was correct!");
						
						//Checks if the answered question is the last one
						if(this.questionsList.size()==1)
						{
							//Ends the game
							System.out.println("Thank you for playing! You won $"+this.userScore+".");
							System.exit(1);
						}
						
						System.out.println("Your current amount of money won: $"+this.userScore);
						System.out.println();
						
						while(true)
						{
							try {
								System.out.println("The next question is worth $"+this.reward+".");
								System.out.println("Would you like to continue the quizz or would you like to walk away with your money?");
								
								System.out.print("Enter y if you want to continue or n if you do not want to: ");
								
								String response=scanner.next();
								
								//Checks if the response entered is longer than one character
								if(response.length()!=1)
									throw new IllegalArgumentException("Enter just one letter.");
								
								//Checks if the letter entered is not y or n
								if(response.equalsIgnoreCase("y")==false && response.equalsIgnoreCase("n")==false)
									throw new IllegalArgumentException("The letter should be y or n.");
								
								if(response.toLowerCase().equals("y"))
								{
									//The previously answered question gets removed from the list of questions
									this.questionsList.remove(questionNumber);
									break;
								}else
								{
									//Ends the game
									System.out.println();
									System.out.println("Thank you for playing! You won $"+this.userScore+".");
									System.exit(1);
								}
								
							}catch(InputMismatchException | IllegalArgumentException e)
							{
								/*Catches the exception and prompts the user to enter either y or n on the next loop
								iteration*/
								System.out.println();
								System.out.println("Please enter y or n.");
							}
						}
						
						
						
					}
				}catch(InputMismatchException | IllegalArgumentException e)
				{
					/*Catches the exception and prompts the user to enter an appropriate number on the next loop
					iteration*/
					System.out.println("Please enter an appropriate number.");
				}
				
				
				
			}
			
		}
	}
	
}

