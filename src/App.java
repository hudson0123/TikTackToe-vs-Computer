import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        boolean turn = true;
        System.out.println("\n0 = vs Friend  |  1 = vs Computer\n");
        System.out.print("Game Mode:  ");
        int mode = input.nextInt();
        TikTakToe game = new TikTakToe(mode);
        game.clearScreen();
        game.setPlayerSymbols('X', 'O');
        game.printBoard();
        while (game.isOver(game.getBoard()) == false) {
            System.out.print("Row Number: ");
            int row = input.nextInt();
            System.out.print("Col Number: ");
            int col = input.nextInt();
            if (game.getMode() == 0 && turn) {
                game.takeTurn(0, row, col, false);
                turn = false;
            } else if (game.getMode() == 0 && !turn) {
                game.takeTurn(1, row, col, false);
                turn = true;
            } else {
                game.takeTurn(0, row, col, false);
            }
        }
    }
}
