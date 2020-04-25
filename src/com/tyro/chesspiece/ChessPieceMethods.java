package com.tyro.chesspiece;

/**
 * Define common methods to be used by Child classes of ChessPiece.
 */
public interface ChessPieceMethods {
    // Return formatted result of legal moves as a String.
    String getValidMoves();
    // Put all legal moves into a List.
    void getAllLegalMoves();
}
