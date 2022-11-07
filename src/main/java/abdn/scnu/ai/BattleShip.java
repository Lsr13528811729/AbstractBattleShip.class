package abdn.scnu.ai;

import java.util.Random;

public class BattleShip extends AbstractBattleShip {

    public BattleShip(String name) {
        this.name = name;

        // 随机初始化船的方向
        Random random = new Random();
        boolean flag = random.nextBoolean();
        this.shipOrientation = flag ? HORIZONTAL : VERTICAL;
    }


    @Override
    public boolean checkAttack(int row, int column) {
        if (getHits() >= 3) {
            return false;
        }
        for (int[] shipCoordinate : getShipCoordinates()) {
            if (shipCoordinate[0] == row && shipCoordinate[1] == column) {
                this.hits++;
                return true;
            }
        }

        return false;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getHits() {
        return this.hits;
    }

    @Override
    public String getShipOrientation() {
        return this.shipOrientation;
    }

    @Override
    public void setHits(int numberOfHits) {
        this.hits = numberOfHits;
    }

    @Override
    public int[][] getShipCoordinates() {
        return this.shipCoordinates;
    }

    @Override
    public void setShipCoordinates(int[][] coordinates) {
        // 数组拷贝 防止外界被修改
        this.shipCoordinates = coordinates.clone();
    }
}
