package abdn.scnu.ai;

import java.util.Scanner;

public class RunGame {

    public static void main(String[] args) {

        // 入参数量检查
        if (args.length < 3) {
            System.out.println("初始化参数不完整");
            return;
        }
        int row = Integer.parseInt(args[0]);
        int col = Integer.parseInt(args[1]);
        int numberOfShips = Integer.parseInt(args[2]);

        // 战场宽高小于3 战船放不下
        if (row < 3 || col < 3) {
            System.out.println("棋盘宽度&高度不可小于3");
            return;
        }

        // 初始化游戏
        Game game = new Game(row, col, numberOfShips);
        try {
            doRunGame(game);
        } catch (Exception e) {
            // 程序全局异常处理：打印异常日志后退出
            System.out.println("游戏出现未知异常，请参考异常日志");
            e.printStackTrace();
        }


    }

    /**
     * 执行游戏流程
     */
    private static void doRunGame(Game game) {
        // 开始游戏 直到一方获胜或者用户退出
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("\nPlease enter the position you wish to attack");
            String input = scanner.nextLine();
            // 检查是否退出游戏 如果输入的是exit 程序结束
            game.exitGame(input);
            // 游戏继续进行
            game.playRound(input);

        } while (!game.checkVictory());
    }
}
