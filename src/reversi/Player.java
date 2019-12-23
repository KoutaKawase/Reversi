package reversi;

import java.util.HashMap;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Player プレイヤーに関するデータあつかう役目
 */
public class Player {
    private final Piece pieceColor;
    public Boolean isCurrent;

    public Player(Piece pieceColor, Boolean isCurrent) {
        this.pieceColor = pieceColor;
        this.isCurrent = isCurrent;
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
    public HashMap<String, Integer> inputColumnAndRow() {
        HashMap<String, Integer> selectedRowAndColumn = new HashMap<String, Integer>();
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Column: ");
            selectedRowAndColumn.put("column", scanner.nextInt());
            System.out.print("Row: ");
            selectedRowAndColumn.put("row", scanner.nextInt());
            if (isInvalidInputNumber(selectedRowAndColumn)) {
                throw new InputMismatchException("適切な範囲内で入力してください");
            }
        } catch (InputMismatchException e) {
            System.out.println("数字以外は入力できません");
            e.printStackTrace();
        } finally {
            scanner.close();
        }

        return selectedRowAndColumn;
    }

}