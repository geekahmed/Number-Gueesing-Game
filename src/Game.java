import java.util.Random; // Random Class to generate random numbers.

/*
    Main game class which contains the message, number of tries, true guess.
 */
public class Game {
    private String message = "";
    private final int true_guess;
    private int number_of_tries;

    // Constructor for generated a custom game depending on the difficulty.
    private Game(int number_of_tries, int upper_bound) {
        this.number_of_tries = number_of_tries;
        final long seed = System.currentTimeMillis(); // Generates different number every game.
        Random rand = new Random(seed);
        // Generates Random number between 1 and the upper bound.
        this.true_guess = rand.nextInt(upper_bound) + 1;
        this.message = "I'm thinking of a number between 1 and " + upper_bound;
    }

    // Generate Hard Game
    public static Game generateHardGame() {
        return new Game(5, 1000);
    }

    // Generate Medium Game
    public static Game generateMediumGame() {
        return new Game(10, 600);
    }

    // Generate Easy Game
    public static Game generateEasyGame() {
        return new Game(15, 100);
    }


    // Checking if the guess is true.
    public boolean checkGuess(int guessValue) {
        if (guessValue == true_guess) {
            message = "You guessed it correctly!";
            return true;
        } else if (number_of_tries == 1) {
            message = "Game Over";
            return false;

        } else if (guessValue < true_guess) {
            message = guessValue + " is too small.";
            number_of_tries--;
            return false;
        } else {
            message = guessValue + " is too large.";
            number_of_tries--;
            return false;
        }
    }

    /*
        Getters for message, number of tries, and true guess.
     */
    public String getMessage() {
        return message;
    }


    public int getTrue_guess() {
        return true_guess;
    }


    public int getNumber_of_tries() {
        return number_of_tries;
    }


}
