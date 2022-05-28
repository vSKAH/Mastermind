package fr.skoupi.mastermind.party;

/*
 *  * @Created on 11/05/2022
 *  * @Project Mastermind
 *  * @Author Jimmy  / SKAH#7513
 */

import java.util.List;
import java.util.Scanner;

public class MastermindParty {

    private final List<Character> trustedColors = List.of('R', 'N', 'B', 'J', 'V', 'G', 'O');
    private char[] secretColors;

    private int currentPlayer;

    private final Scanner scanner;

    public MastermindParty() {
        scanner = new Scanner(System.in);
    }

    public void startParty() {

        currentPlayer = 1;
        int playerTrys = 0;

        System.out.println("Bienvenue dans le jeu Mastermind");

        printColorsListMessage();
        System.out.println(currentPlayerString());


        while (scanner.hasNextLine()) {
            String input = scanner.next();

            if (currentPlayer == 1) {
                if (input.length() == 5) {
                    char[] inputCharacters = input.toCharArray();
                    boolean isValid = true;

                    for (char c : inputCharacters) {
                        if (!trustedColors.contains(c)) {
                            isValid = false;
                            break;
                        }
                    }

                    if(isValid) {
                        secretColors = inputCharacters;
                        currentPlayer++;
                        System.out.println(currentPlayerString() + " Combinaison : Bien placé : Mal placé");
                    }
                    else printColorsListMessage();

                } else printColorsListMessage();
            } else {
                if (input.length() == 5) {

                    playerTrys++;
                    int good = 0;
                    int bad = 0;

                    char[] playerColors = input.toCharArray();
                    StringBuilder formattedResponse = new StringBuilder();

                    for (int i = 0; i < playerColors.length; i++) {
                        char playerChar = playerColors[i];

                        if(!trustedColors.contains(playerChar)) {
                            printColorsListMessage();
                            return;
                        }

                        formattedResponse.append(playerChar).append(" ");

                        if (playerChar == secretColors[i]) {
                            good++;
                        }
                        else bad++;
                    }

                    formattedResponse.append(" : ").append(good).append(" : ").append(bad);
                    System.out.println("Essai n°" + playerTrys + " : " + formattedResponse);

                    if (good == 5) {
                        String congratulationWord = "";
                        if (playerTrys <= 5) congratulationWord = "Bravo";
                        if (playerTrys > 5 && playerTrys <= 10) congratulationWord = "Correct";
                        if (playerTrys > 10) congratulationWord = "Décevant";
                        System.out.println(congratulationWord + " ! Vous avez réussi en " + playerTrys + " essais.");
                        System.exit(0);
                    }
                }
                else printColorsListMessage();


            }
        }

    }


    private void printColorsListMessage() {
        if (currentPlayer == 1)
            System.out.printf("Veuillez choisir une combinaison de 5 couleurs. %s\n", trustedColors);
        else
            System.out.printf("Combinaison invalide. Veuillez choisir une combinaison de 5 couleurs. %s\n", trustedColors);

    }

    private String currentPlayerString() {
        if (currentPlayer == 1) return "1er joueur : ";
        return "2eme joueur :";
    }

}
