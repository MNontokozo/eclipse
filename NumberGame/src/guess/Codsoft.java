
package guess;

import java.util.Scanner;
import java.util.Random;

import java.util.Scanner;
import java.util.Random;

public class Codsoft {
    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);
        
        Random random = new Random();
        
        // Generate a random number between 1 and 100
        int targetNumber = random.nextInt(100) + 1;
        
        // Limit the number of attempts
        int maxAttempts = 10;
        int attempts = 0;
        
        // Prompt user to guess the number
        while (attempts < maxAttempts) {
            System.out.print("Attempt " + (attempts + 1) + "/" + maxAttempts + ". Guess the number between 1 and 100: ");
            int userGuess = scanner.nextInt();
            attempts++;
            
            // Check if the guess is correct
            if (userGuess == targetNumber) {
                System.out.println("Congratulations! You've guessed the correct number: " + targetNumber);
                System.out.println("Your score is: " + (maxAttempts - attempts + 1));
                break;
            } else if (userGuess < targetNumber) {
                System.out.println("Too low.");
            } else {
                System.out.println("Too high.");
            }
        }
        
        // If the user couldn't guess the number within the attempts limit
        if (attempts == maxAttempts) {
            System.out.println("Sorry, you've reached the maximum number of attempts.");
            System.out.println("The correct number was: " + targetNumber);
        }
        
       
        scanner.close();
    }
}

