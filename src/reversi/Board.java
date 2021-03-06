package reversi;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Board ボードの状態を管理する役
 */
public class Board {
    public static final int MAX_BOARD_COLUMN = 6;
    public static final int MAX_BOARD_ROW = 6;
    public static final int FINISH_PIECES_COUNT = 36;
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

    public Boolean isAllFilled() {
        Boolean isAllFilled = false;
        int pieceCount = 0;
        // 全てピースを数えFINISH_PIECES_COUNTと等しければ全て埋まっていると見なす
        for (Piece[] columnPiece : this.boardState) {
            pieceCount += Arrays.asList(columnPiece).stream()
                    .filter(row -> row == Piece.BLACK_PIECE || row == Piece.WHITE_PIECE).count();
        }

        if (pieceCount == Board.FINISH_PIECES_COUNT) {
            isAllFilled = true;
        }

        return isAllFilled;
    }

    public String getBoardAsString() {
        return this.toString();
    }

    public Piece[][] getBoardState() {
        return this.boardState;
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
                        board += column + "|   |";
                        break;
                    }
                    board += "   |";
                    break;
                }
            }
            board += "\n +---+---+---+---+---+---+\n";
        }
        return board;
    }

    // TODO: リファクタと自分で説明できるように理解したい
    private Boolean canPutDown(int column, int row, int vectorColumn, int vectorRow, Player player) {
        Piece currentPiece = player.getPieceColor();

        // 指定された場所へ移動
        column += vectorColumn;
        row += vectorRow;

        if ((column < 0 || column >= Board.MAX_BOARD_COLUMN) || (row < 0 || row >= Board.MAX_BOARD_ROW)) {
            return false;
        }

        if (this.boardState[column][row] == currentPiece) {
            return false;
        }

        if (this.boardState[column][row] == Piece.NONE) {
            return false;
        }

        column += vectorColumn;
        row += vectorRow;

        while ((0 <= column && column < Board.MAX_BOARD_COLUMN) && (0 <= row && row < Board.MAX_BOARD_ROW)) {

            if (this.boardState[column][row] == Piece.NONE) {
                return false;
            }

            if (this.boardState[column][row] == currentPiece) {
                return true;
            }

            column += vectorColumn;
            row += vectorRow;
        }
        return false;
    }

    private Boolean canPutDown(int column, int row, Player player) {
        if (column >= Board.MAX_BOARD_COLUMN || row >= Board.MAX_BOARD_ROW) {
            return false;
        }

        if (this.boardState[column][row] != Piece.NONE) {
            return false;
        }

        // 右
        if (canPutDown(column, row, 0, 1, player)) {
            return true;
        }
        // 下
        if (canPutDown(column, row, 1, 0, player)) {
            return true;
        }
        // 左
        if (canPutDown(column, row, 0, -1, player)) {
            return true;
        }
        // 上
        if (canPutDown(column, row, -1, 0, player)) {
            return true;
        }
        // 右下
        if (canPutDown(column, row, 1, 1, player)) {
            return true;
        }
        // 左上
        if (canPutDown(column, row, -1, -1, player)) {
            return true;
        }
        // 右上
        if (canPutDown(column, row, -1, 1, player)) {
            return true;
        }
        // 左下
        if (canPutDown(column, row, 1, -1, player)) {
            return true;
        }
        System.out.println("置ける所がありません");
        System.out.println("PASS");
        return false;
    }

    // TODO: ここらへんオーバーロードが酷いのでリファクタしたい コメントもつけて処理を理解
    private void reverse(int column, int row, int vectorColumn, int vectorRow, Player player) {
        Piece currentPiece = player.getPieceColor();

        column += vectorColumn;
        row += vectorRow;

        while (this.boardState[column][row] != currentPiece) {
            this.boardState[column][row] = currentPiece;

            column += vectorColumn;
            row += vectorRow;
        }
    }

    private void reverse(int column, int row, Player player) {
        // 右
        if (canPutDown(column, row, 0, 1, player)) {
            reverse(column, row, 0, 1, player);
        }
        // 下
        if (canPutDown(column, row, 1, 0, player)) {
            reverse(column, row, 1, 0, player);
        }
        // 左
        if (canPutDown(column, row, 0, -1, player)) {
            reverse(column, row, 0, -1, player);
        }
        // 上
        if (canPutDown(column, row, -1, 0, player)) {
            reverse(column, row, -1, 0, player);
        }
        // 右下
        if (canPutDown(column, row, 1, 1, player)) {
            reverse(column, row, 1, 1, player);
        }
        // 左上
        if (canPutDown(column, row, -1, -1, player)) {
            reverse(column, row, -1, -1, player);
        }
        // 右上
        if (canPutDown(column, row, -1, 1, player)) {
            reverse(column, row, -1, 1, player);
        }
        // 左下
        if (canPutDown(column, row, 1, -1, player)) {
            reverse(column, row, 1, -1, player);
        }
    }

    private void setBoardState(Integer column, Integer row, Player player) {
        // バリデーション処理を追加予定
        if (canPutDown(column, row, player)) {
            this.boardState[column][row] = player.getPieceColor();
            reverse(column, row, player);
        }
    }

    public void changeBoardState(HashMap<String, Integer> input, Board board, Player player) {
        board.setBoardState(input.get("column"), input.get("row"), player);
    }
}
