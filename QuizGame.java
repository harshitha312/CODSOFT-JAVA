import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
class Question {
    private String questionText;
    private List<String> options;
    private int correctAnswerIndex;
    public Question(String questionText, List<String> options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }
    public String getQuestionText() {
        return questionText;
    }
    public List<String> getOptions() {
        return options;
    }
    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}
class Quiz {
    private List<Question> questions;
    private int score;
    private List<Boolean> results;
    public Quiz() {
        this.questions = new ArrayList<>();
        this.score = 0;
        this.results = new ArrayList<>();
    }
    public void addQuestion(Question question) {
        questions.add(question);
    }
    public void start() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.getQuestionText());
            List<String> options = question.getOptions();
            for (int j = 0; j < options.size(); j++) {
                System.out.println((j + 1) + ". " + options.get(j));
            }
            Timer timer = new Timer();
            QuizTimerTask timerTask = new QuizTimerTask(scanner, question.getCorrectAnswerIndex(), this);
            timer.schedule(timerTask, 30000); // 30 seconds timer
            int userAnswer = scanner.nextInt();
            timerTask.setUserAnswer(userAnswer);
            timer.cancel();
        }
        displayResults();
    }
    public void submitAnswer(int userAnswer, int correctAnswerIndex) {
        if (userAnswer == correctAnswerIndex + 1) {
            score++;
            results.add(true);
        } else {
            results.add(false);
        }
    }
    public void displayResults() {
        System.out.println("Quiz Over!");
        System.out.println("Your Score: " + score + "/" + questions.size());
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.getQuestionText());
            System.out.println("Your answer was " + (results.get(i) ? "correct" : "incorrect"));
        }
    }
}
class QuizTimerTask extends TimerTask {
    private Scanner scanner;
    private int correctAnswerIndex;
    private Quiz quiz;
    private boolean isAnswered;
    private int userAnswer;
    public QuizTimerTask(Scanner scanner, int correctAnswerIndex, Quiz quiz) {
        this.scanner = scanner;
        this.correctAnswerIndex = correctAnswerIndex;
        this.quiz = quiz;
        this.isAnswered = false;
    }
    @Override
    public void run() {
        if (!isAnswered) {
            System.out.println("Time's up!");
            quiz.submitAnswer(-1, correctAnswerIndex); // -1 indicates no answer
        }
    }
    public void setUserAnswer(int userAnswer) {
        if (!isAnswered) {
            this.userAnswer = userAnswer;
            isAnswered = true;
            quiz.submitAnswer(userAnswer, correctAnswerIndex);
        }
    }
}
public class QuizGame {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        List<String> options1 = new ArrayList<>();
        options1.add("London");
        options1.add("Delhi");
        options1.add("New York");
        options1.add("Kathmandu");
        quiz.addQuestion(new Question("What is the capital of India?", options1, 1));
        List<String> options2 = new ArrayList<>();
        options2.add("Earth");
        options2.add("Jupiter");
        options2.add("Mars");
        options2.add("Uranus");
        quiz.addQuestion(new Question("Which planet is known as the Red Planet?", options2, 2));
        List<String> options3 = new ArrayList<>();
        options3.add("Atlantic");
        options3.add("Indian");
        options3.add("Arctic");
        options3.add("");
        quiz.addQuestion(new Question("What is the largest Continent on Earth?", options3, 3));
        quiz.start();
    }
}
