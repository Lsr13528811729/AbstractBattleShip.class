package abdn.scnu.ai;

public class PlayerGameGrid extends GameGrid {

    public PlayerGameGrid(int width, int height, int numberOfShips) {
        super(width, height, numberOfShips);
    }

    public void printGrid() {
        String[][] gameGrid = this.gameGrid.clone();
        // 添加战船
//        for (AbstractBattleShip ship : this.ships) {
//            if (ship.getHits() >= 3) {
//                continue;
//            }
//            int[][] shipCoordinates = ship.getShipCoordinates();
//            for (int[] shipCoordinate : shipCoordinates) {
//                int row = shipCoordinate[0];
//                int col = shipCoordinate[1];
//                if (gameGrid[row][col] == ".") {
//                    gameGrid[row][col] = "*";
//                }
//            }
//        }
        for (String[] lines : gameGrid) {
            for (String line : lines) {
                System.out.print(line + " ");
            }
            System.out.print("\n");
        }
    }

}
