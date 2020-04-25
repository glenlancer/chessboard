package com.tyro.chesspiece;

import com.tyro.chessboard.ChessBoardApp;

/**
 * define operations for a pawn piece.
 */
public class Pawn extends ChessPiece implements ChessPieceMethods {

    /**
     * constructor.
     */
    public Pawn(boolean isWhitePiece, String position,
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

        int curFile = file;
        int curRank = getNextRank(rank);

        // check forward positions for a pawn.
        // If it's at its initial position, then possibily we need to check two.
        if (onBoard(curFile, curRank) &&
            checkForwardPosition(curFile, curRank) &&
            atInitialPosition(rank)) {
            curRank = getNextRank(curRank);
            if (onBoard(curFile, curRank)) {
                checkForwardPosition(curFile, curRank);
            }
        }

        // check 2 possibly capture positions for a pawn.
        curFile = file - 1;
        curRank = getNextRank(rank);
        if (onBoard(curFile, curRank)) {
            checkCapturePosition(curFile, curRank);
        }
        curFile = file + 1;
        curRank = getNextRank(rank);
        if (onBoard(curFile, curRank)) {
            checkCapturePosition(curFile, curRank);
        }
    }

    /**
     * check if the pawn is at its initial position, based on its color.
     */
    private boolean atInitialPosition(int rank) {
        if (isWhitePiece && rank == 2) {
            return true;
        } else if (!isWhitePiece && rank == 7) {
            return true;
        }
        return false;
    }

    /**
     * check if the pawn can be moved to a foward position.
     */
    private boolean checkForwardPosition(int file, int rank) {
        if (!chessBoardApp.hasPieceAt(file, rank)) {
            legalMoves.add(chessBoardApp.getPosition(file, rank));
            return true;
        }
        return false;
    }

    /**
     * check if the pawn can be moved to a capture position.
     */
    private void checkCapturePosition(int file, int rank) {
        if (chessBoardApp.hasPieceAt(file, rank) &&
            !chessBoardApp.hasPieceAtWithColour(file, rank, this.isWhitePiece)) {
            legalMoves.add(chessBoardApp.getPosition(file, rank));
        }
    }

    /**
     * get next possible rank for the pawn after movement, based on its color.
     */
    private int getNextRank(int rank) {
        if (isWhitePiece) {
            return rank + 1;
        }
        return rank - 1;
    }
}
