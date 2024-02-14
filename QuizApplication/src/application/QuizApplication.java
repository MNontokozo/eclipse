package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

// Class to represent each question in the quiz
class Question {
    private String question;
    private List<String> options;
    private int correctOptionIndex;

    // Constructor to initialize question, options, and correct option index
    public Question(String question, List<String> options, int correctOptionIndex) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    // Getter methods for question, options, and correct option index
    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}

// Class to manage the quiz application
public class QuizApplication {
    private List<Question> questions;
    private int currentQuestionIndex;
    private int score;
    private Timer timer;
    private boolean answered;

    // Constructor to initialize variables
    public QuizApplication() {
        this.questions = new ArrayList<>();
        this.currentQuestionIndex = 0;
        this.score = 0;
        this.timer = new Timer();
    }

    // Method to add a question to the quiz
    public void addQuestion(Question question) {
        questions.add(question);
    }

    // Method to start the quiz
    public void startQuiz() {
        System.out.println("Welcome to the Quiz!");
        displayNextQuestion();
    }

    // Method to display the next question
    private void displayNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question question = questions.get(currentQuestionIndex);
            System.out.println("\nQuestion " + (currentQuestionIndex + 1) + ": " + question.getQuestion());
            List<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }
            startTimer();
            answered = false;
        } else {
            endQuiz();
        }
    }

    // Method to start the timer for each question
    private void startTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up!");
                displayAnswerResult(-1);
            }
        }, 10000); // 10 seconds timer
    }

    // Method to submit the answer for the current question
    public void submitAnswer(int optionIndex) {
        if (!answered) {
            displayAnswerResult(optionIndex);
            answered = true;
        }
    }

    // Method to display the result of the submitted answer
    private void displayAnswerResult(int selectedOptionIndex) {
        timer.cancel();
        Question question = questions.get(currentQuestionIndex);
        int correctOptionIndex = question.getCorrectOptionIndex();

        if (selectedOptionIndex == correctOptionIndex) {
            System.out.println("Correct!");
            score++;
        } else if (selectedOptionIndex == -1) {
            System.out.println("Time's up! The correct answer was option " + (correctOptionIndex + 1));
        } else {
            System.out.println("Incorrect! The correct answer was option " + (correctOptionIndex + 1));
        }

        currentQuestionIndex++;
        displayNextQuestion();
    }

    // Method to end the quiz and display final score
    private void endQuiz() {
        System.out.println("\nQuiz ended!");
        System.out.println("Your final score: " + score + "/" + questions.size());

        System.out.println("\nSummary of correct/incorrect answers:");
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            int correctOptionIndex = question.getCorrectOptionIndex();
            String result = (correctOptionIndex == i) ? "Correct" : "Incorrect";
            System.out.println("Question " + (i + 1) + ": " + result);
        }
    }

    // Main method to run the quiz application
    public static void main(String[] args) {
        // Create a quiz object
        QuizApplication quiz = new QuizApplication();

        // Add questions to the quiz
        Question q1 = new Question("What is the capital of France?",
                List.of("London", "Paris", "Rome", "Berlin"), 1);
        Question q2 = new Question("What is 2 + 2?", List.of("3", "4", "5", "6"), 1);
        Question q3 = new Question("Which planet is closest to the sun?",
                List.of("Earth", "Mars", "Venus", "Mercury"), 3);

        quiz.addQuestion(q1);
        quiz.addQuestion(q2);
        quiz.addQuestion(q3);

        // Start the quiz
        quiz.startQuiz();

        // Get user input and submit answers
        Scanner scanner = new Scanner(System.in);
        while (quiz.currentQuestionIndex < quiz.questions.size()) {
            System.out.print("Enter your answer (1-" + quiz.questions.get(0).getOptions().size() + "): ");
            int option = scanner.nextInt();
            quiz.submitAnswer(option - 1);
        }
        scanner.close();
    }
}
