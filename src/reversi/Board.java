package reversi;

/**
 * Board ボードの状態を管理する役
 */
public class Board {
    public static final int MAX_BOARD_COLUMN = 6;
    public static final int MAX_BOARD_ROW = 6;
    private Piece[][] boardState = new Piece[MAX_BOARD_COLUMN][MAX_BOARD_ROW];

    public Board() {
        for (int column = 0; column < MAX_BOARD_COLUMN; column++) {
            for (int row = 0; row < MAX_BOARD_ROW; row++) {
                // 中央2*2に左上と右下に白、左下と右上に黒を配置
                if ((column == 2 && row == 2) || (column == 3 && row == 3)) {
                    boardState[column][row] = Piece.WHITE_PIECE;
                    continue;
                }

                if ((column == 3 && row == 2) || (column == 2 && row == 3)) {
                    boardState[column][row] = Piece.BLACK_PIECE;
                    continue;
                }

                boardState[column][row] = Piece.NONE;
            }
        }
    }

    public String getBoardState() {
        return this.toString();
    }

    public String toString() {
        String board = "";
        // TODO: ボードを整形する
        for (int column = 0; column < MAX_BOARD_COLUMN; column++) {
            for (int row = 0; row < MAX_BOARD_ROW; row++) {
                switch (this.boardState[column][row]) {
                case BLACK_PIECE:
                    board += "■ ";
                    break;
                case WHITE_PIECE:
                    board += "□ ";
                    break;
                case NONE:
                    board += "# ";
                    break;
                }
            }
            board += "\n";
        }
        return board;
    }
}