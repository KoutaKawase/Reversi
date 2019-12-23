package reversi;

import java.util.HashMap;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Player プレイヤーに関するデータあつかう役目
 */
public class Player {
    private final Piece pieceColor;
    public Boolean isMyTurn;

    public Player(Piece pieceColor, Boolean isMyTurn) {
        this.pieceColor = pieceColor;
        this.isMyTurn = isMyTurn;
    }

    public Piece getPieceColor() {
        return this.pieceColor;
    }

    private Boolean isInvalidInputNumber(HashMap<String, Integer> input) {
        int MAX_BOARD_COLUMN = Board.MAX_BOARD_COLUMN;
        int MAX_BOARD_ROW = Board.MAX_BOARD_ROW;
        int inputColumn = input.get("column");
        int inputRow = input.get("row");

        if (inputColumn >= MAX_BOARD_COLUMN || inputRow >= MAX_BOARD_ROW) {
            return true;
        }

        return false;
    }

    // TODO: 例外処理が酷いのでリファクタリングしたい
    public HashMap<String, Integer> inputColumnAndRow(Scanner scanner) {
        HashMap<String, Integer> selectedRowAndColumn = new HashMap<String, Integer>();
        try {
            System.out.print("Column: ");
            selectedRowAndColumn.put("column", scanner.nextInt());
            System.out.print("Row: ");
            selectedRowAndColumn.put("row", scanner.nextInt());
            if (isInvalidInputNumber(selectedRowAndColumn)) {
                System.out.println("適切な範囲内の数値を入力してください");
                System.exit(1);
            }
        } catch (InputMismatchException e) {
            System.out.println("数字以外は入力できません");
            System.exit(1);
        }

        return selectedRowAndColumn;
    }

}