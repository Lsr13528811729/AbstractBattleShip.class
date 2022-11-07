package abdn.scnu.ai;

public class OpponentGameGrid extends GameGrid {

    public OpponentGameGrid(int width, int height, int numberOfShips) {
        super(width, height, numberOfShips);
    }

    public void printGrid() {

        String[][] gameGrid = this.gameGrid;

        for (String[] lines : gameGrid) {
            for (String line : lines) {
                if (line == "*") {
                    System.out.print(". ");
                } else {
                    System.out.print(line + " ");
                }
            }
            System.out.print("\n");
        }
    }

}
