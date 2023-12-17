# Java Quiz Application

This Java project is a simple quiz application developed in Java.

## Project Structure

### Files
- `Test.java`, `Question.java`, `Quizz.java`: Source code files for various components of the quiz application.
- `Test.class`, `Question.class`, `Quizz.class`: Compiled class files generated from the respective Java source files.
- `Questions.csv`: Contains quiz questions and related information.

### Description

This project implements a Java-based quiz application that loads questions from the `Questions.csv` file and allows users to answer them. The program uses the `Test`, `Question`, and `Quizz` classes to manage the quiz functionality.

## How to Run the Application

### Using Eclipse:

1. **Download the Project:**
   - Visit the GitHub repository page at https://github.com/your-username/your-repo.
   - Click on the "Code" button and select "Download ZIP".
   - Extract the downloaded ZIP file to a directory on your local machine.

2. **Import the Project into Eclipse:**
   - Open Eclipse IDE.
   - Go to "File" -> "Import...".
   - Select "General" -> "Existing Projects into Workspace".
   - Click on "Next".
   - Choose the root directory by clicking on "Browse" and selecting the extracted project folder.
   - Click "Finish".
   - Your Java project will now appear in the Project Explorer within Eclipse.

3. **Run the Application:**
   - In the Project Explorer, locate and right-click on the `Test.java` file.
   - Select "Run As" -> "Java Application".
   - The quiz application will start executing.

### Using Visual Studio Code:

1. **Download and Clone the Project:**
   - Visit the GitHub repository page at https://github.com/your-username/your-repo.
   - Click on the "Code" button and copy the repository URL.
   - Open Visual Studio Code.
   - Open the integrated terminal in VS Code (`Ctrl+Shift+``).
   - Clone the repository by running:
     ```
     git clone <repository-url>
     ```

2. **Open the Project in Visual Studio Code:**
   - In VS Code, open the folder containing the cloned project.

3. **Run the Application:**
   - Open the integrated terminal in VS Code (`Ctrl+Shift+``).
   - Navigate to the directory containing the Java files using the `cd` command.
   - Compile the Java files using: `javac *.java`.
   - Run the application using: `java Test`.
   - The quiz application will start executing.

## CSV File Format

The `Questions.csv` file contains quiz questions in a specific format. Each row represents a question with columns for attributes like question text, options, correct answer, etc.

### Example Questions in `Questions.csv`:

Question Text,Correct Answer,Question Category,Wrong Answer 1,Wrong Answer 2,Wrong Answer 3
What is the capital of France?,Paris,Geography,London,Berlin,Madrid
Who won the FIFA World Cup in 2018?,France,Football,Brazil,Germany,Argentina
... (Other questions)


## Notes
- Ensure the `Questions.csv` file remains in the same directory as the Java files for proper functioning of the application.
