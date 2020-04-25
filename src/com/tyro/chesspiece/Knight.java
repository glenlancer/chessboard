package com.tyro.chesspiece;

import com.tyro.chessboard.ChessBoardApp;

/**
 * define operations for a knight piece.
 */
public class Knight extends ChessPiece implements ChessPieceMethods {

    /**
     * constructor.
     */
    public Knight(boolean isWhitePiece, String position,
        ChessBoardApp chessBoardApp) {
        super(isWhitePiece, position, chessBoardApp);
    }

    /**
     * get formatted output of all valid moves for current piece.
     */
    @Override
    public String getValidMoves() {
        getAllLegalMoves();
        sortLegalMoves();
        return formattedResult;
    }

    /**
     * get all legal moves for current piece and put them in a List.
     */
    @Override
    public void getAllLegalMoves() {
        int file = (int)(position.charAt(0) - 'a') + 1;
        int rank = (int)(position.charAt(1) - '0');

        legalMoves.clear();

        // check all 8 possible position for a knight.
        int curFile = file - 2;
        int curRank = rank + 1;
        if (onBoard(curFile, curRank)) {
            checkPosition(curFile, curRank);
        }
        curFile = file - 2;
        curRank = rank - 1;
        if (onBoard(curFile, curRank)) {
            checkPosition(curFile, curRank);
        }
        curFile = file - 1;
        curRank = rank + 2;
        if (onBoard(curFile, curRank)) {
            checkPosition(curFile, curRank);
        }
        curFile = file - 1;
        curRank = rank - 2;
        if (onBoard(curFile, curRank)) {
            checkPosition(curFile, curRank);
        }
        curFile = file + 1;
        curRank = rank + 2;
        if (onBoard(curFile, curRank)) {
            checkPosition(curFile, curRank);
        }
        curFile = file + 1;
        curRank = rank - 2;
        if (onBoard(curFile, curRank)) {
            checkPosition(curFile, curRank);
        }
        curFile = file + 2;
        curRank = rank + 1;
        if (onBoard(curFile, curRank)) {
            checkPosition(curFile, curRank);
        }
        curFile = file + 2;
        curRank = rank - 1;
        if (onBoard(curFile, curRank)) {
            checkPosition(curFile, curRank);
        }
    }
}
