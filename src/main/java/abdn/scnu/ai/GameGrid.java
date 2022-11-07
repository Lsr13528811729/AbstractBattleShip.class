package abdn.scnu.ai;

import java.util.Arrays;
import java.util.Random;

public class GameGrid extends AbstractGameGrid {

    private int width;

    private int height;

    public GameGrid(int width, int height, int numberOfShips) {
        this.gameGrid = new String[height][width];
        this.width = width;
        this.height = height;
        generateShips(numberOfShips);
        initializeGrid();
        for (AbstractBattleShip ship : ships) {
            placeShip(ship);
        }
    }

    @Override
    public void initializeGrid() {
        for (String[] rows : gameGrid) {
            Arrays.fill(rows, ".");
        }
    }

    /**
     * 更新棋盘
     */
    public void refreshGrid() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (gameGrid[row][col].equals("*")) {
                    gameGrid[row][col] = ".";
                }
            }
        }
        for (AbstractBattleShip ship : ships) {
            for (int[] shipCoordinate : ship.shipCoordinates) {
                if (gameGrid[shipCoordinate[0]][shipCoordinate[1]].equals(".")) {
                    gameGrid[shipCoordinate[0]][shipCoordinate[1]] = "*";
                }
            }
        }

    }

    @Override
    public void placeShip(AbstractBattleShip ship) {
        int rowLimit;
        int colLimit;
        if (ship.shipOrientation.equals(AbstractBattleShip.HORIZONTAL)) {
            rowLimit = height;
            colLimit = width - 2;
        } else {
            rowLimit = height - 2;
            colLimit = width;
        }
        Random random = new Random();
        int headRow = random.nextInt(rowLimit);
        int headCol = random.nextInt(colLimit);
        int[][] shipCoordinates = new int[3][2];
        if (ship.shipOrientation.equals(AbstractBattleShip.HORIZONTAL)) {
            shipCoordinates[0] = new int[]{headRow, headCol};
            shipCoordinates[1] = new int[]{headRow, headCol + 1};
            shipCoordinates[2] = new int[]{headRow, headCol + 2};
        } else {
            shipCoordinates[0] = new int[]{headRow, headCol};
            shipCoordinates[1] = new int[]{headRow + 1, headCol};
            shipCoordinates[2] = new int[]{headRow + 2, headCol};
        }

        for (int[] shipCoordinate : shipCoordinates) {
            if (gameGrid[shipCoordinate[0]][shipCoordinate[1]].equals(".")) {
                gameGrid[shipCoordinate[0]][shipCoordinate[1]] = "*";
            }
        }
        ship.setShipCoordinates(shipCoordinates);
    }

    @Override
    public void generateShips(int numberOfShips) {
        this.ships = new AbstractBattleShip[numberOfShips];
        for (int i = 0; i < numberOfShips; i++) {
            ships[i] = new BattleShip("Ship " + (i + 1));
        }
    }
}
