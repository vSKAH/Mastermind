package fr.skoupi.mastermind;

/*
 *  * @Created on 11/05/2022
 *  * @Project Mastermind
 *  * @Author Jimmy  / SKAH#7513
 */

import java.util.List;
import java.util.Scanner;

public class MastermindParty {

    //List of characters to use in the game. the characters represent the colors available in the game.
    private final List<Character> trustedColors = List.of('R', 'N', 'B', 'J', 'V', 'G', 'O');
    //Character Array to store the secret color code defined by the player 1
    private char[] secretColors;

    //current player number :  1 or 2
    private int currentPlayer;

    //Scanner to read the input from the user
    private final Scanner scanner;

    public MastermindParty() {
        //defined the scanner to read the input from the user

        scanner = new Scanner(System.in);
    }

    /**
     * Start the game
     */
    public void startParty() {

        //set the current player to 1
        currentPlayer = 1;
        //create variable to store the number of player 2 try
        int playerTrys = 0;

        //say hello to player one
        System.out.println("Bienvenue dans le jeu Mastermind");

        //Displays a console message to say on player one to enter the secret color code
        printColorsListMessage();
        //Displays a console message to say which player's turn it is
        System.out.println(currentPlayerString());

        //As long as the scanner is not closed and the return value of hasNextLine is true
        while (scanner.hasNextLine()) {

            //read the input from the user
            String input = scanner.next();

            //If the player who writes is player 1
            if (currentPlayer == 1) {
                //If the input has 5 characters
                if (input.length() == 5) {
                    //create char array and store the characters of the input
                    char[] inputCharacters = input.toCharArray();
                    //create boolean and set default value is false, return true if the input doesn't contain any character not in the trustedColors list
                    boolean isValid = true;

                    //iterate over the input characters
                    for (char c : inputCharacters) {
                        //if the trustedColors list doesn't contain the current iterate isValid is set to false and break the loop.
                        if (!trustedColors.contains(c)) {
                            isValid = false;
                            break;
                        }
                    }

                    //if isValid boolean is true set the secretColors variable to the input characters and increment the currentPlayer variable
                    //If the player who writes is player 1
                    if (isValid) {
                        secretColors = inputCharacters;
                        currentPlayer++;
                        System.out.println(currentPlayerString() + " Combinaison : Bien placé : Mal placé");
                    }
                    //if the input is not valid, display a console message to say the colors than can be used
                    else printColorsListMessage();

                }
                //if the input has not 5 characters, display a console message to say the colors than can be used
                else printColorsListMessage();
            }
            //if the turn of player 2
            else {
                //if the input of player 2 has 5 characters
                if (input.length() == 5) {

                    //increment player try
                    playerTrys++;
                    //create vars for the good and bad colors
                    int good = 0;
                    int bad = 0;

                    //create char array and store the characters of the input of player 2
                    char[] playerColors = input.toCharArray();
                    //create StringBuilder, this string builder is used to format the response displayed to the user
                    StringBuilder formattedResponse = new StringBuilder();

                    //create loop to iterate over the player 2 input. This loop has the start value at 0 and the end value at 4 because the input is a char array
                    //and if the char array doesn't have 5 characters, the loop will not be executed
                    for (int i = 0; i < playerColors.length; i++) {

                        //The element currently iterated in the input of player 2
                        char playerChar = playerColors[i];

                        //if the currently iterated char is not a trusted color print the valid colors
                        if (!trustedColors.contains(playerChar)) {
                            printColorsListMessage();
                            //return to not execute next
                            return;
                        }

                        //add the player char in the formattedResponse StringBuilder and add spaces between the chars
                        formattedResponse.append(playerChar).append(" ");

                        //if the player char is the same as the secret char increment the good variable
                        if (playerChar == secretColors[i]) {
                            good++;
                        }
                        //if the player char is not the same as the secret char increment the bad variable
                        else bad++;
                    }


                    //add the good number and bad number of colors to the formattedResponse StringBuilder
                    formattedResponse.append(" : ").append(good).append(" : ").append(bad);
                    //display the formatted response to the user in the console
                    System.out.println("Essai n°" + playerTrys + " : " + formattedResponse);

                    //if the good number is 5, the player 2 won the game
                    if (good == 5) {
                        //create var string and assign value later to display the winner message
                        String congratulationWord = "";
                        //if the player 2 try is < or equals to 5 display the message "Bravo"
                        if (playerTrys <= 5) congratulationWord = "Bravo";
                        //if the player 2 try is > to 5 and is < to 10 display the message "Correct"
                        if (playerTrys > 5 && playerTrys <= 10) congratulationWord = "Correct";
                        //if player 2 try is > to 10 display the message "Décevent"
                        if (playerTrys > 10) congratulationWord = "Décevant";
                        //display the congratulation word and the number of tries to the user
                        System.out.println(congratulationWord + " ! Vous avez réussi en " + playerTrys + " essais.");
                        //close the scanner
                        scanner.close();
                        //end the game
                        System.exit(0);
                    }
                }
                //if the input has not 5 characters, display a console message to say the colors than can be used
                else printColorsListMessage();


            }
        }

    }


    private void printColorsListMessage() {
        //if the current player turn is player 1
        if (currentPlayer == 1)
            System.out.printf("Veuillez choisir une combinaison de 5 couleurs. %s\n", trustedColors);
            //else if the current player turn is not the player 1
        else
            System.out.printf("Combinaison invalide. Veuillez choisir une combinaison de 5 couleurs. %s\n", trustedColors);

    }

    /**
     * This method returns a string that says which player's turn it is
     * @return String that says which player's turn it is
     */
    private String currentPlayerString() {
        //if the current player turn is player 1 return the string "1er joueur : "
        if (currentPlayer == 1) return "1er joueur : ";
        //if the current player turn is not the player 1 return the string "2ème joueur : "
        return "2eme joueur :";
    }

}
