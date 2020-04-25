package com.tyro.chessboard;

import com.tyro.chesspiece.*;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * define logic for chess board.
 */
public class ChessBoardApp {
    private Map<String, ChessPiece> chessBoard;  // store all pieces
    private List<String> chessOrder;             // preserve the input order
    private String errorMessage;                 // store error message

    /**
     * constructor.
     */
    public ChessBoardApp() {
        chessBoard = new HashMap<>();
        chessOrder = new ArrayList<>();
    }

    /**
     * generate the position String based on file and rank.
     */
    public String getPosition(int file, int rank) {
        return Character.toString((char)('a' + file - 1)) + rank;
    }

    /**
     * check if a position has a piece on it.
     */
    public boolean hasPieceAt(int file, int rank) {
        String position = getPosition(file, rank);
        return chessBoard.containsKey(position);
    }

    /**
     * check if a position has a piece with a certain color on it.
     */
    public boolean hasPieceAtWithColour(int file, int rank, boolean isWhite) {
        String position = getPosition(file, rank);
        ChessPiece chessPiece = chessBoard.get(position);
        if (chessPiece != null) {
            return chessPiece.isWhitePiece() == isWhite;
        }
        return false;
    }

    /**
     * add a new piece to the chess board.
     */
     public boolean addPiece(boolean isWhitePiece, char type, String position) {
        if (chessBoard.get(position) != null) {
            errorMessage = "Position occupied!";
            return false;
        }
        ChessPiece chessPiece;
        if (type == 'P') {
            chessPiece = new Pawn(isWhitePiece, position, this);
            // check a special rule for pawns.
            if (position.charAt(1) == '1' || position.charAt(1) == '8') {
                errorMessage = "Pawns may not be placed on the first or last ranks." +
                    "  Please re-enter.";
                return false;
            }
        } else if (type == 'N') {
            chessPiece = new Knight(isWhitePiece, position, this);
        } else {
            chessPiece = new Bishop(isWhitePiece, position, this);
        }
        chessBoard.put(position, chessPiece);
        chessOrder.add(position);
        return true;
    }

    /**
     * get chess board error message.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * output valid moves for each piece on the chess board.
     */
    public void findValidMoves() {
        System.out.println("\nValid moves");
        for (String position : chessOrder) {
            ChessPiece chessPiece = chessBoard.get(position);
            String pieceColour = "Black";
            char type;
            String validMoves;
            if (chessPiece.isWhitePiece()) {
                pieceColour = "White";
            }
            if (chessPiece instanceof Pawn) {
                type = 'P';
                validMoves = ((Pawn)chessPiece).getValidMoves();
            } else if (chessPiece instanceof Knight) {
                type = 'N';
                validMoves = ((Knight)chessPiece).getValidMoves();
            } else {
                type = 'B';
                validMoves = ((Bishop)chessPiece).getValidMoves();
            }
            System.out.println(pieceColour + " " + type +
                " on " + position +": " + validMoves);
        }
    }

    /**
     * clear the chess board.
     */
    public void clear() {
        chessBoard.clear();
        chessOrder.clear();
    }
}
