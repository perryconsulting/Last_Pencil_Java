package lastpencil;

public class PencilGame {
    private final int numPencils;
    private final String playerOne;
    private final String playerTwo;

    public PencilGame(int numPencils, String playerOne, String playerTwo) {

        this.numPencils = numPencils;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public int getNumPencils() {
        return numPencils;
    }

    public String getPlayerOne() {
        return playerOne;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }

    public String currentPencilList(int pencilCount) {
        return "|".repeat(Math.max(0, pencilCount));
    }
}
