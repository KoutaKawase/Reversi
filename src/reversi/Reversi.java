package reversi;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Reversi {
    private static String buildCurrentTurnMessage(Player currentPlayer) {
        if (currentPlayer.getPieceColor() == Piece.BLACK_PIECE) {
            return "現在のターン: 黒 ■";
        }
        return "現在のターン: 白 □";
    }

    public static void main(String[] args) throws Exception {
        // 2人のプレイヤーがそれぞれ黒番と白番のどちらを担当するかを決める
        Player playerBlack = new Player(Piece.BLACK_PIECE, true);
        Player playerWhite = new Player(Piece.WHITE_PIECE, false);
        // 盤面中央の4マスに黒と白の石を2つずつ置く。右上と左下が黒、左上と右下が白になるように互い違いに配置する。
        Board board = new Board();
        Player currentPlayer;
        HashMap<String, Integer> selectedRowAndColumn;
        Scanner scanner = new Scanner(System.in);
        Boolean isGameLoopEnabled = true;

        while (isGameLoopEnabled) {
            currentPlayer = playerBlack.isMyTurn ? playerBlack : playerWhite;

            // リファクタリングはあと
            System.out.println(board.getBoardAsString());
            // 黒番、白番の順で交互に盤面の空いているマスに自分の色の石を打っていく

            System.out.println(buildCurrentTurnMessage(currentPlayer));
            selectedRowAndColumn = currentPlayer.inputColumnAndRow(scanner);
            board.changeBoardState(selectedRowAndColumn, board, currentPlayer);
            // TODO: メソッドにうつしてリファクタ
            for (Piece[] columnPieces : board.getBoardState()) {
                // NONEが存在しなければ全てピースで埋まったと見なす
                Boolean isAllFilled = Arrays.asList(columnPieces).contains(Piece.NONE);

                if (!isAllFilled) {
                    isGameLoopEnabled = false;
                    break;
                }
            }

            if (currentPlayer == playerBlack) {
                currentPlayer.isMyTurn = false;
                playerWhite.isMyTurn = true;
            } else {
                currentPlayer.isMyTurn = false;
                playerBlack.isMyTurn = true;
            }
        }
        scanner.close();
        Piece[][] endTimeBoardState = board.getBoardState();
        int blackPieceCount = 0;
        int whitePieceCount = 0;
        for (Piece[] columnPieces : endTimeBoardState) {
            blackPieceCount += Collections.frequency(Arrays.asList(columnPieces), playerBlack.getPieceColor());
            whitePieceCount += Collections.frequency(Arrays.asList(columnPieces), playerWhite.getPieceColor());
        }
        System.out.println("BLACK: " + blackPieceCount + "個 WHITE: " + whitePieceCount + "個");
        if (blackPieceCount > whitePieceCount) {
            System.out.println("WINNER: " + "黒");
        }
        if (whitePieceCount > blackPieceCount) {
            System.out.println("WINNER: " + "白");
        }
        System.out.println("また遊んでね！！！");
    }
}