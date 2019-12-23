package reversi;

import java.util.HashMap;

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

    // TODO: クソ密集してるので小さいメソッドに分けてリファクタリング
    public String toString() {
        String board = "";
        for (int column = 0; column < MAX_BOARD_COLUMN; column++) {
            if (column == 0) {
                System.out.println("   0   1   2   3   4   5 ");
                System.out.println(" +---+---+---+---+---+---+");
            }
            for (int row = 0; row < MAX_BOARD_ROW; row++) {
                switch (this.boardState[column][row]) {
                case BLACK_PIECE:
                    if (row == 0) {
                        board += column + "| ■ |";
                        break;
                    }
                    board += " ■ |";
                    break;
                case WHITE_PIECE:
                    if (row == 0) {
                        board += column + "| □ |";
                        break;
                    }
                    board += " □ |";
                    break;
                case NONE:
                    if (row == 0) {
                        board += column + "| # |";
                        break;
                    }
                    board += " # |";
                    break;
                }
            }
            board += "\n +---+---+---+---+---+---+\n";
        }
        return board;
    }

    private void setBoardState(Integer column, Integer row, Player player) {
        // バリデーション処理を追加予定

        this.boardState[column][row] = player.getPieceColor();
    }

    public void changeBoardState(HashMap<String, Integer> input, Board board, Player player) {
        board.setBoardState(input.get("column"), input.get("row"), player);
        System.out.println(board.getBoardState());
    }
}
