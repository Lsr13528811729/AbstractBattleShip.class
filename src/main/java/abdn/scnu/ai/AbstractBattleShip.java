package abdn.scnu.ai;


public abstract class AbstractBattleShip {
    /**
     * 战船方向常量
     */
    public static final String HORIZONTAL = "horizontal";
    public static final String VERTICAL = "vertical";

    protected String name;

    protected int hits;

    protected String shipOrientation;

    protected int[][] shipCoordinates;


    public abstract boolean checkAttack(int row, int column);

    public abstract String getName();

    public abstract int getHits();

    public abstract String getShipOrientation();

    public abstract void setHits(int numberOfHits);

    public abstract int[][] getShipCoordinates();

    public abstract void setShipCoordinates(int[][] coordinates);


}
