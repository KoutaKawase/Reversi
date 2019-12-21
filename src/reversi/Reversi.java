package reversi;

public class Reversi {
    public static void main(String[] args) throws Exception {
        // 2人のプレイヤーがそれぞれ黒番と白番のどちらを担当するかを決める
        Player playerBlack = new Player(PieceColor.BLACK_PIECE);
        Player playerWhite = new Player(PieceColor.WHITE_PIECE);

        System.out.println(playerBlack);
        System.out.println(playerWhite);
    }
}