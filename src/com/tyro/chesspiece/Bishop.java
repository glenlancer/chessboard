package com.tyro.chesspiece;

import com.tyro.chessboard.ChessBoardApp;

/**
 * define operations for a bishop piece.
 */
public class Bishop extends ChessPiece implements ChessPieceMethods {

    /**
     * constructor.
     */
    public Bishop(boolean isWhitePiece, String position,
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

        // check top left direction.
        int curFile = file - 1;
        int curRank = rank + 1;
        while (onBoard(curFile, curRank)) {
            if (!checkPosition(curFile, curRank)) {
                break;
            }
            curFile--;
            curRank++;
        }

        // check bottom left direction.
        curFile = file - 1;
        curRank = rank - 1;
        while (onBoard(curFile, curRank)) {
            if (!checkPosition(curFile, curRank)) {
                break;
            }
            curFile--;
            curRank--;
        }

        // check top right direction.
        curFile = file + 1;
        curRank = rank + 1;
        while (onBoard(curFile, curRank)) {
            if (!checkPosition(curFile, curRank)) {
                break;
            }
            curFile++;
            curRank++;
        }

        // check bottom right direction.
        curFile = file + 1;
        curRank = rank - 1;
        while (onBoard(curFile, curRank)) {
            if (!checkPosition(curFile, curRank)) {
                break;
            }
            curFile++;
            curRank--;
        }
    }
}
