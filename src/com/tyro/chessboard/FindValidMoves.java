package com.tyro.chessboard;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * define input & output logic for FindValidMoves program, can call
 * ChessBoardApp to get result.
 */
public class FindValidMoves {
    private ChessBoardApp chessBoardApp;
    private BufferedReader inFromUser;

    /**
     * read chess pieces configuration from standard input.
     */
    private void readPieces(int numberOfPieces) {
        int pieceIndex = 1;
        char colour;
        boolean isWhitePiece;
        char type;
        String position;
        while (numberOfPieces > 0) {
            try {
                System.out.println("\nPiece " + pieceIndex);
                System.out.print("\nEnter colour (W/B): ");
                colour = inFromUser.readLine().charAt(0);
                if (colour != 'W' && colour != 'B') {
                    throw new Exception("Illegal chess piece colour!");
                }
                isWhitePiece = colour == 'W' ? true : false;
                System.out.print("Enter type (B/N/P): ");
                type = inFromUser.readLine().charAt(0);
                if (type != 'B' && type != 'N' && type != 'P') {
                    throw new Exception("Illegal chess piece type!");
                }
                System.out.print("Enter position: ");
                position = inFromUser.readLine();
                if (position.length() != 2 ||
                    (position.charAt(0) < 'a' || position.charAt(0) > 'h') ||
                    (position.charAt(1) < '1' || position.charAt(1) > '8')) {
                    throw new Exception("Illegal chess piece position!");
                }
                if (!chessBoardApp.addPiece(isWhitePiece, type, position)) {
                    System.out.println(chessBoardApp.getErrorMessage());
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Illegal inputs: " + e.getMessage());
                continue;
            }
            pieceIndex++;
            numberOfPieces--;
        }
    }

    /**
     * read user input & execute find valid moves logic.
     */
    public void run() {
        chessBoardApp = new ChessBoardApp();
        inFromUser = new BufferedReader(new InputStreamReader(System.in));
        int numberOfPieces;

        // process user input and generate output.
        while (true) {
            System.out.print("\nEnter number of pieces: ");
            try {
                numberOfPieces = Integer.parseInt(inFromUser.readLine());
            } catch (Exception e) {
                System.out.println("Please enter an integer!");
                continue;
            }

            // read configuration for each piece.
            readPieces(numberOfPieces);
            chessBoardApp.findValidMoves();

            // check if user wants to continue.
            if (!ifContinue()) {
                break;
            }

            // clear the chess board after each run.
            chessBoardApp.clear();
        }
    }

    /**
     * check if user wants to continue.
     */
    private boolean ifContinue() {
        while (true) {
            System.out.print("\nContinue (Y/N)?: ");
            try {
                char input = inFromUser.readLine().charAt(0);
                if (input == 'Y') {
                    return true;
                } else if (input == 'N') {
                    return false;
                }
            } catch (Exception e) {
                // Do nothing.
            }
            System.out.println("Please enter 'Y' or 'N'!");
        }
    }

    /**
     * program entry.
     */
    public static void main(String[] args) {
        FindValidMoves findValidMoves = new FindValidMoves();
        findValidMoves.run();
    }
}
