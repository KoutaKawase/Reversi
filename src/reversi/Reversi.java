package reversi;

public class Reversi {
    public static void main(String[] args) throws Exception {
        // 2人のプレイヤーがそれぞれ黒番と白番のどちらを担当するかを決める
        Player playerBlack = new Player(Piece.BLACK_PIECE);
        Player playerWhite = new Player(Piece.WHITE_PIECE);
        // 盤面中央の4マスに黒と白の石を2つずつ置く。右上と左下が黒、左上と右下が白になるように互い違いに配置する。
        Board board = new Board();
        board.initializeBoard();
        System.out.println(board.getBoardState());
    }
}