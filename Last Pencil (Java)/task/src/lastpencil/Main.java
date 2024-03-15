package lastpencil;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String player1;
        String player2;

        int numPencils = initializePencilCount(scanner);
        String[] playerNames = determinePlayers(scanner);
        player1 = playerNames[0];
        player2 = playerNames[1];

        PencilGame newGame = new PencilGame(numPencils, player1, player2);
        int remainingPencils = newGame.getNumPencils();
        String currentPlayer = newGame.getPlayerOne();

        playGame(newGame, remainingPencils, currentPlayer, scanner);
    }

    private static void playGame(PencilGame newGame, int remainingPencils, String currentPlayer, Scanner scanner) {
        do {
            System.out.println(newGame.currentPencilList(remainingPencils));
            System.out.printf("%s's turn!%n", currentPlayer);
            int playerMove = 0;

            if (currentPlayer.equals("John")) {
                boolean validMove = false;
                while (!validMove) {
                    try {
                        String temp = scanner.nextLine();
                        playerMove = Integer.parseInt(temp);
                    } catch (NumberFormatException e) {
                        System.out.println("Possible values: '1', '2' or '3'");
                        continue;
                    }
                    if (playerMove < 1 || playerMove > 3) {
                        System.out.println("Possible values: '1', '2' or '3'");
                        continue;
                    }
                    if (playerMove > remainingPencils) {
                        System.out.println("Too many pencils were taken");
                        continue;
                    }
                    validMove = true;
                }
            } else {
                playerMove = jackBotMove(remainingPencils);
                System.out.println(playerMove);
            }
            remainingPencils -= playerMove;
            currentPlayer = currentPlayer.equals(newGame.getPlayerOne()) ? newGame.getPlayerTwo() : newGame.getPlayerOne();

        } while (remainingPencils > 0);
        System.out.printf("%s won!%n", currentPlayer);
    }

    private static int jackBotMove(int remainingPencils) {
        Random random = new Random();
        int botMove;
        if (remainingPencils == 1 || remainingPencils == 2) {
            botMove = 1;
        } else if (remainingPencils == 3) {
            botMove = 2;
        } else if (remainingPencils % 4 == 0) {
            botMove = 3;
        } else if ((remainingPencils + 1) % 4 == 0) {
            botMove = 2;
        } else if (( remainingPencils + 2) % 4 == 0) {
            botMove = 1;
        } else {
            botMove = random.nextInt(3) + 1;
        }

        return botMove;
    }

    private static String[] determinePlayers(Scanner scanner) {
        String[] playerNames = new String[2];
        System.out.println("Who will be the first (John, Jack):");
        boolean validPlayers = false;
        String firstPlayer;
        String secondPlayer;
        while (!validPlayers) {
            try {
                firstPlayer = scanner.nextLine();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (firstPlayer.equals("John")) {
                secondPlayer = "Jack";
            } else if (firstPlayer.equals("Jack")) {
                secondPlayer = "John";
            } else {
                System.out.println("Choose between 'John' and 'Jack'");
                continue;
            }
            validPlayers = true;
            playerNames[0] = firstPlayer;
            playerNames[1] = secondPlayer;
        }
        return playerNames;
    }

    private static int initializePencilCount(Scanner scanner) {
        int numberOfPencils = 0;
        System.out.println("How many pencils would you like to use:");
        boolean validEntry = false;
        while (!validEntry) {
            try {
                String temp = scanner.nextLine();
                numberOfPencils = Integer.parseInt(temp);
                if (numberOfPencils < 1) {
                    System.out.println("The number of pencils should be positive");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
                continue;
            }
            validEntry = true;
        }
        return numberOfPencils;
    }
}
