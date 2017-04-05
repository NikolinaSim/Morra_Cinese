import java.util.Random;
import java.util.Scanner;
 
public class MorraCinese {
    private Player player;
    private Computer computer;
    private int modeCC; //game mode Computer-Computer
    private int modePC; //mode game Person-Computer
    private int scorePlayer1;
    private int scorePlayer2;
    private int gameNumber;
    private int userSelection;

 
    private enum Gesture {
        ROCK, PAPER, SCISSORS;
 
        /**
         * Compares this gesture with another to determine a tie, win, or
         * loss.
         */
        public int compareGestures(Gesture otherGesture) {
            // Tie
            if (this == otherGesture)
                return 0;
 
            switch (this) {
            case ROCK:
                return (otherGesture == SCISSORS ? 1 : -1);
            case PAPER:
                return (otherGesture == ROCK ? 1 : -1);
            case SCISSORS:
                return (otherGesture == PAPER ? 1 : -1);
            }
 
            
            return 0;
        }
    }
 
    private class Player {
        private Scanner input;
 
        public Player() {
            input = new Scanner(System.in);
        }
 
        public Gesture getGesture() {
            
            System.out.println("Rock, paper, or scissors? ");
 
            // Get input of the player input
            String playerInput = input.nextLine();
            playerInput = playerInput.toUpperCase();
            char firstLetter = playerInput.charAt(0);
            if (firstLetter == 'R' || firstLetter == 'P' || firstLetter == 'S') {
                // Player has entered a valid input
                switch (firstLetter) {
                case 'R':
                    return Gesture.ROCK;
                case 'P':
                    return Gesture.PAPER;
                case 'S':
                    return Gesture.SCISSORS;
                }
            }
 
            // Player has not entered a valid input. Show the gesture option again
            return getGesture();
        }
 
        // If the player wants to play another round
        public boolean anotherRound() {
            System.out.print("\nDo you want to play another round? (Y/N)");
            String userInput = input.nextLine();
            userInput = userInput.toUpperCase();
            return userInput.charAt(0) == 'Y';
        }
    }
 
    private class Computer {
        public Gesture getGesture() {
            Gesture[] gestures = Gesture.values();
            Random rand = new Random();
            int index = rand.nextInt(gestures.length);
            return gestures[index];
        }
    }
 
    public MorraCinese() {
        player = new Player();
        computer = new Computer();
        scorePlayer1 = 0;
        scorePlayer2 = 0;
        gameNumber = 0;
        modeCC=0;
        modePC=0;
    }
 
    public void startGame() {
    	
        Scanner inputScanner = new Scanner(System.in);
        
        
        if(modeCC==0 && modePC==0)
        {
        System.out.println("WELCOME TO MORRA");
        System.out.println("If you want to see a simulation of the game with two players insert 1 or if you want to play against the computer insert 2");
        userSelection = Integer.parseInt(inputScanner.nextLine());
        }
        
        // User selects to see a autosimulation of the game - Computer against Computer mode
        if(userSelection==1 || modeCC==1 ){
        	modeCC=1;
        	Gesture c1 = computer.getGesture();
        	Gesture c2 = computer.getGesture();
            System.out.println("\nPlayer 1 played " + c1 + ".");
            
            
            
         // Compare the gestures and check who is the winner
            int compareGestures = c1.compareGestures(c2);
            switch (compareGestures) {
            case 0: // Tie
                System.out.println("Tie!");
                break;
            case 1: // Player 1 wins
                System.out.println(c1 + " beats " + c2 + ". Player 1 won the round.");
                scorePlayer1++;
                break;
            case -1: // Computer 2 wins
                System.out.println(c2 + " beats " + c1 + ". Player 2 won the round.");
                scorePlayer2++;
                break; }
            gameNumber++;
            
        }
        // User selects to play against the computer - Player against Computer mode
        else if(userSelection==2 || modePC==1){
        // Get the gestures
        modePC=1;
        Gesture playerGesture = player.getGesture();
        Gesture computerGesture = computer.getGesture();
        System.out.println("\nYou played " + playerGesture + ".");
        System.out.println("Computer played " + computerGesture + ".\n");
 
        // Compare the gestures and determine winner
        int compareGestures = playerGesture.compareGestures(computerGesture);
        switch (compareGestures) {
        case 0: // Tie
            System.out.println("Tie!");
            break;
        case 1: // Player wins
            System.out.println(playerGesture + " beats " + computerGesture + ". You won!");
            scorePlayer1++;
            break;
        case -1: // Computer wins
            System.out.println(computerGesture + " beats " + playerGesture + ". You lost!");
            scorePlayer2++;
            break;
        }
        gameNumber++;
        }  
        
        else{
        	System.out.println("Please enter a valid option 1 or 2 to proceed");
            startGame();
        }
        
        // Ask if the player wants to play again
        if (player.anotherRound()) {
            System.out.println();
            startGame();
        } else {
            printScoreboard();
        }
        
    }
 
    /**
     * Shows the Scoreboard of the played game if the user doesn't want to play anymore.
     */ 
    private void printScoreboard() {
        int numWins = scorePlayer1; 
        int numLosses = scorePlayer2;
        int numTies = gameNumber - scorePlayer1 - scorePlayer2;
        
        System.out.print("FINAL SCOREBOARD: \n");
        
        System.out.print("*");
        printDash(55);
        System.out.println("*");
        
        
        String title1= "PLAYER 1";
        String title2= "PLAYER 2";
        
        // Displays certain title if the user plays agains the computer
        if(modePC==1)
        {
        	title1="WINS";
        	title2="LOSSES";
        }
 
        // Print the titles
        System.out.printf("|  %9s  |  %9s  |  %6s  |  %12s  |\n",
                title1, title2, "TIES", "GAMES PLAYED");
 
        
        System.out.print("|");
        printDash(10);
        System.out.print("*");
        printDash(10);
        System.out.print("*");
        printDash(10);
        System.out.print("*");
        printDash(22);
        
        System.out.println("|");
 
        // Print the results
        System.out.printf("|  %9d  |  %9d  |  %6d  |  %12d  |\n",
                numWins, numLosses, numTies, gameNumber);
 
        
        System.out.print("*");
        printDash(55);
        System.out.println("*");
    }
 
    private void printDash(int numDashes) {
        for (int i = 0; i < numDashes; i++) {
            System.out.print("-");
        }
    }
 
    public static void main(String[] args) {
        MorraCinese game = new MorraCinese();
        game.startGame();
    }
}