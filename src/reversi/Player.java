package reversi;

import java.util.HashMap;

/**
 * Player プレイヤーに関するデータあつかう役目
 */
public class Player {
    private final Piece pieceColor;

    public Player(final Piece pieceColor) {
        this.pieceColor = pieceColor;
    }

    public HashMap<String, Integer> inputColumnAndRow() {
        HashMap<String, Integer> playerInput = new HashMap<String, Integer>();
        playerInput.put("column", 3);
        playerInput.put("row", 5);
        return playerInput;
    }
}