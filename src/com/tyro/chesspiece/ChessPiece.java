package com.tyro.chesspiece;

import com.tyro.chessboard.ChessBoardApp;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * root class of chess piece, define common attributes and methods.
 */
public class ChessPiece {
    protected boolean isWhitePiece;        // if piece is white or black
    protected String position;             // current position on board, e.g. a1
    protected List<String> legalMoves;     // list of positions
    protected String formattedResult;      // formatted output String
    protected ChessBoardApp chessBoardApp; // reference to chess board

    /**
     * constructor.
     */
    public ChessPiece(boolean isWhitePiece, String position,
        ChessBoardApp chessBoardApp) {
        this.isWhitePiece = isWhitePiece;
        this.position = position;
        this.chessBoardApp = chessBoardApp;
        legalMoves = new ArrayList<>();
    }

    /**
     * getter function for isWhitePiece.
     */
    public boolean isWhitePiece() {
        return isWhitePiece;
    }

    /**
     * getter function for position.
     */
    public String getPosition() {
        return position;
    }

    /**
     * check if input (file, rank) is a valid position on chess board.
     */
    protected boolean onBoard(int file, int rank) {
        return file >= 1 && file <= 8 && rank >= 1 && rank <= 8;
    }

    /**
     * generate formatted output for legal moves on chess board for
     * current chess piece.
     */
    protected void sortLegalMoves() {
        Collections.sort(legalMoves);
        formattedResult = "[";
        if (legalMoves.size() >= 1) {
            formattedResult += legalMoves.get(0);
            legalMoves.remove(0);
        }
        for (String moves : legalMoves) {
            formattedResult += ", " + moves;
        }
        formattedResult += "]";
    }

    /**
     * Check position (file, rank) on chess board. If there is no piece
     * on it, then add it to legalMoves and return true; otherwise, return
     * false and add it to legalMoves if there is a piece of different
     * color there.
     */
     protected boolean checkPosition(int file, int rank) {
         if (!chessBoardApp.hasPieceAt(file, rank)) {
            legalMoves.add(chessBoardApp.getPosition(file, rank));
            return true;
        } else if (!chessBoardApp.hasPieceAtWithColour(file, rank, isWhitePiece)) {
            legalMoves.add(chessBoardApp.getPosition(file, rank));
        }
        return false;
    }
}
