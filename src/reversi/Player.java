package reversi;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Player プレイヤーに関するデータあつかう役目
 */
public class Player {
    private final Piece pieceColor;

    public Player(final Piece pieceColor) {
        this.pieceColor = pieceColor;
    }

    public HashMap<String, Integer> inputColumnAndRow() {
        HashMap<String, Integer> selectedRowAndColumn = new HashMap<String, Integer>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Column: ");
        selectedRowAndColumn.put("column", scanner.nextInt());
        System.out.print("Row: ");
        selectedRowAndColumn.put("row", scanner.nextInt());
        scanner.close();
        return selectedRowAndColumn;
    }
}