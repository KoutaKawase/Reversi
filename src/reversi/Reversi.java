package reversi;

import java.util.HashMap;

public class Reversi {
    public static void main(String[] args) throws Exception {
        // 2人のプレイヤーがそれぞれ黒番と白番のどちらを担当するかを決める
        Player playerBlack = new Player(Piece.BLACK_PIECE, true);
        Player playerWhite = new Player(Piece.WHITE_PIECE, false);
        // 盤面中央の4マスに黒と白の石を2つずつ置く。右上と左下が黒、左上と右下が白になるように互い違いに配置する。
        Board board = new Board();
        Player currentPlayer = playerBlack.isCurrent ? playerBlack : playerWhite;
        // リファクタリングはあと
        System.out.println(board.getBoardState());
        // 黒番、白番の順で交互に盤面の空いているマスに自分の色の石を打っていく
        HashMap<String, Integer> selectedRowAndColumn = currentPlayer.inputColumnAndRow();
        board.changeBoardState(selectedRowAndColumn, board);
    }
}