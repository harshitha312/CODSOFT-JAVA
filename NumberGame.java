import java.util.Random;
import java.util.Scanner;
public class NumberGame {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            String ans;
            do {
                Random rand = new Random();
                int num = rand.nextInt(100) + 1;
                System.out.println("Number is generated successfully.");
                System.out.println("You have 3 chances. Please guess the number:");
                boolean correctGuess = false;
                
                for (int i = 0; i < 3; i++) {
                    int guess = input.nextInt();
                    if(guess<num){
                        System.out.println("Your guess is low. Try guessing higher next time.");
                    }
                    else if(guess>num){
                        System.out.println("Your guess is high. Try guessing lower next time.");
                    }
                    else if(guess==num){
                        System.out.println("Your guess is correct!");
                    }
                    if (i == 2 && !correctGuess) {
                        System.out.print("The generated number is: ");
                        System.out.println(num);
                        System.out.println("All the chances have been used.");
                    } else if (!correctGuess) {
                        System.out.println("Please try again.");
                    }
                }
                input.nextLine();  // Clear the buffer
                System.out.println("Would you like to play again? (Y/N)");
                ans = input.nextLine();
            } while (ans.equalsIgnoreCase("y"));
        }
    }
}
