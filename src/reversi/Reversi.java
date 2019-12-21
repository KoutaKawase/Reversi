package reversi;

public class Reversi {
    public static void main(String[] args) throws Exception {
        // 2人のプレイヤーがそれぞれ黒番と白番のどちらを担当するかを決める
        Player playerBlack = new Player(PieceColor.BLACK_PIECE);
        Player playerWhite = new Player(PieceColor.WHITE_PIECE);
        // 盤面中央の4マスに黒と白の石を2つずつ置く。右上と左下が黒、左上と右下が白になるように互い違いに配置する。
    }
}