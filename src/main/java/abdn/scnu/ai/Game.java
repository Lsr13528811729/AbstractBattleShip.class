package abdn.scnu.ai;

import cn.hutool.core.lang.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game implements GameControls {

    public PlayerGameGrid playerGameGrid;
    public OpponentGameGrid opponentGameGrid;

    public Game(int rowNum, int colNum, int numOfShips) {
        playerGameGrid = new PlayerGameGrid(colNum, rowNum, numOfShips);
        opponentGameGrid = new OpponentGameGrid(colNum, rowNum, numOfShips);
    }

    @Override
    public void playRound(String input) {
        // user action

        // 检测攻击坐标输入是否合法
        String[] inputs = input.split(",");
        if (inputs.length != 2) {
            System.out.println("Incorrect input");
            return;
        }
        int row;
        int col;
        try {
            row = Integer.parseInt(inputs[0]);
            col = Integer.parseInt(inputs[1]);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect input");
            return;
        }

        System.out.println("Player is attacking");
        boolean hit = attackOneGrid(row, col, getOpponentsGrid());
        getOpponentsGrid().refreshGrid();
        // 优化点：如果此时对手全军覆没，跳过对手回合直接返回 避免平局
//        if (hit && allShipDestroy(getOpponentsGrid().ships)) {
//            return;
//        }


        // oppo's action
        // 挑选一个攻击点
        System.out.println("Opponent is attacking");

        Pair<Integer, Integer> attackCoordinate = getAttackCoordinate(getPlayersGrid());
        attackOneGrid(attackCoordinate.getKey(), attackCoordinate.getValue(), getPlayersGrid());
        getPlayersGrid().refreshGrid();

        // 打印双方战场
        System.out.println("Player's Grid");
        getPlayersGrid().printGrid();
        System.out.println("\nOpponent's Grid");
        getOpponentsGrid().printGrid();

    }

    @Override
    public boolean checkVictory() {
        AbstractGameGrid playersGrid = getPlayersGrid();
        AbstractBattleShip[] playersShips = playersGrid.ships;
        if (allShipDestroy(playersShips)) {
            System.out.println("You have lost!");
            return true;
        }

        AbstractGameGrid opponentsGrid = getOpponentsGrid();
        AbstractBattleShip[] opponentsShips = opponentsGrid.ships;
        if (allShipDestroy(opponentsShips)) {
            System.out.println("You have won!");
            return true;
        }
        return false;
    }

    @Override
    public void exitGame(String input) {
        if (input.contains("exit")) {
            System.out.println("\nExiting game – thank you for playing");
            // 结束程序 0表示正常退出
            System.exit(0);
        }
    }

    @Override
    public PlayerGameGrid getPlayersGrid() {
        return playerGameGrid;
    }

    @Override
    public OpponentGameGrid getOpponentsGrid() {
        return opponentGameGrid;
    }

    /**
     * 在一个棋盘上攻击：标记棋盘 输出命中结果
     *
     * @param row  攻击坐标
     * @param col  攻击坐标
     * @param grid 要攻击的棋盘
     * @return 是否命中
     */
    private boolean attackOneGrid(int row, int col, AbstractGameGrid grid) {
        List<String> hitShips = new ArrayList<>();
        for (AbstractBattleShip ship : grid.ships) {
            boolean hit = ship.checkAttack(row, col);
            if (hit) {
                // 记录被击中的船只
                hitShips.add(ship.getName());

            }
        }
        // 没有命中
        if (hitShips.isEmpty()) {
            // 标记棋盘
            grid.gameGrid[row][col] = "%";
            // 输出提示字符
            System.out.println("MISS!!!");
        } else { // 命中了
            // 标记棋盘
            grid.gameGrid[row][col] = "X";
            // 输出命中的船名字
            for (String hitShip : hitShips) {
                System.out.println("HIT " + hitShip + "!!!");
            }
        }

        return !hitShips.isEmpty();
    }

    /**
     * 判断船是否全部被击沉
     *
     * @param ships BattleShip对象数组
     * @return true 全部被击沉
     */
    private boolean allShipDestroy(AbstractBattleShip[] ships) {
        boolean allDestroy = true;
        for (AbstractBattleShip ship : ships) {
            if (ship.getHits() < 3) {
                allDestroy = false;
                break;
            }
        }
        return allDestroy;
    }

    /**
     * 获取一个攻击点
     * 获取策略：随机挑选一个没有攻击过的
     *
     * @param grid
     * @return 攻击点坐标
     */
    private Pair<Integer, Integer> getAttackCoordinate(AbstractGameGrid grid) {
        List<Pair<Integer, Integer>> safeCoordinates = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < grid.gameGrid.length; rowIndex++) {
            String[] row = grid.gameGrid[rowIndex];
            for (int colIndex = 0; colIndex < row.length; colIndex++) {
                if (grid.gameGrid[rowIndex][colIndex].equals(".")) {
                    safeCoordinates.add(new Pair<>(rowIndex, colIndex));
                }
            }
        }
        Collections.shuffle(safeCoordinates);
        return safeCoordinates.get(0);
    }
}
