package reversi;

/**
 * Board ボードの状態を管理する役
 */
public class Board {
    public static final int MAX_BOARD_COLUMN = 8;
    public static final int MAX_BOARD_ROW = 8;
    private Piece[][] boardState = new Piece[MAX_BOARD_COLUMN][MAX_BOARD_ROW];
}