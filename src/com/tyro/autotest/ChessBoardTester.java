package com.tyro.autotest;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.LinkedList;

/**
 * created by Guannan Lin on 23/10/16.
 *
 * a BLACK BOX (not UT) testing tool.
 */
public class ChessBoardTester {

    /**
     * program entry.
     */
    public static void main(String[] args) {
        ChessBoardTester tester = new ChessBoardTester();
        tester.start();
        System.out.println("The tester is running...");
        System.out.println();
    }

    /**
     * start the testing thread.
     */
    private void start() {
        ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", "FindValidMoves.jar");
        try {
            Process process = processBuilder.start();
            InputStream stdout = process.getInputStream();
            OutputStream stdin = process.getOutputStream();
            AutoTester autotester = new AutoTester(stdout, stdin);
            Thread t_autotester = new Thread(autotester);
            t_autotester.start();
        } catch (IOException e) {
            System.err.println("I/O Exception: " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * AutoTester class.
     */
    private class AutoTester implements Runnable {
        private BufferedReader reader;
        private PrintStream writer;
        private int caseNumber;
        LinkedList<String> stringList = new LinkedList<String>();

        /**
         * constructor. Listen on program's standard input and output.
         */
        public AutoTester(InputStream inputStream, OutputStream outputStream) {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            writer = new PrintStream(outputStream);
        }

        /**
         * assert output before a new line.
         */
        public void readsPrompt(String prompt) throws IOException, AssertionError {
            char[] cbuf = new char[prompt.length()];
            reader.read(cbuf);
            assert (new String(cbuf)).equals(prompt);
        }

        /**
         * assert multiple lines of output.
         */
        public void assertOutputs()
            throws IOException, AssertionError {
            LinkedList<String> outputList = new LinkedList<String>();

            for (int i = 0; i < stringList.size(); i++) {
                outputList.add(reader.readLine());
            }

            assert outputList.equals(stringList);
            stringList.clear();
        }

        /**
         * assert config for one chess piece.
         */
        public void assertOneChessPieceConfig(int index, char colour,
            char type, String position) throws IOException, AssertionError {
            readsPrompt("\nPiece " + index);
            readsPrompt("\n");
            readsPrompt("\nEnter colour (W/B): ");
            writer.println(colour);
            writer.flush();
            readsPrompt("Enter type (B/N/P): ");
            writer.println(type);
            writer.flush();
            readsPrompt("Enter position: ");
            writer.println(position);
            writer.flush();
        }

        /**
         * assert continue prompts.
         */
        public void assertContinueProgram()
        	throws IOException, AssertionError {
            readsPrompt("\nContinue (Y/N)?: ");
            writer.println('Y');
            writer.flush();
        }

        /**
         * terminate program.
         */
        public void assertTerminateProgram() throws IOException, AssertionError {
            readsPrompt("\nContinue (Y/N)?: ");
            writer.println('N');
            writer.flush();
        }

        /**
         * run test cases.
         */
        public void run() {
            try {
                // Main flow tests:
                System.out.println("Main flow testing...");
                // Case 1: Single pawn on board, at d4.
                caseNumber = 1;
                readsPrompt("\nEnter number of pieces: ");
                writer.println("1");
                writer.flush();
                assertOneChessPieceConfig(1, 'W', 'P', "d4");
                stringList.add("");
                stringList.add("Valid moves");
                stringList.add("White P on d4: [d5]");
                assertOutputs();
                System.out.println("Test case " + caseNumber + " PASSed!");

                assertContinueProgram();

                // Case 2: Single pawn on board, at d2.
                caseNumber = 2;
                readsPrompt("\nEnter number of pieces: ");
                writer.println("1");
                writer.flush();
                assertOneChessPieceConfig(1, 'W', 'P', "d2");
                stringList.add("");
                stringList.add("Valid moves");
                stringList.add("White P on d2: [d3, d4]");
                assertOutputs();
                System.out.println("Test case " + caseNumber + " PASSed!");

                assertContinueProgram();

                // Case 3: Single pawn on board, at d2.
                caseNumber = 3;
                readsPrompt("\nEnter number of pieces: ");
                writer.println("1");
                writer.flush();
                assertOneChessPieceConfig(1, 'B', 'P', "d7");
                stringList.add("");
                stringList.add("Valid moves");
                stringList.add("Black P on d7: [d5, d6]");
                assertOutputs();
                System.out.println("Test case " + caseNumber + " PASSed!");

                assertContinueProgram();

                // Case 4: Single pawn on board, at d8 or d1.
                caseNumber = 4;
                readsPrompt("\nEnter number of pieces: ");
                writer.println("1");
                writer.flush();
                assertOneChessPieceConfig(1, 'W', 'P', "d8");
                stringList.add("Pawns may not be placed on the first or last ranks.  Please re-enter.");
                assertOutputs();
                assertOneChessPieceConfig(1, 'W', 'P', "d1");
                stringList.add("Pawns may not be placed on the first or last ranks.  Please re-enter.");
                assertOutputs();
                assertOneChessPieceConfig(1, 'W', 'P', "a3");
                stringList.add("");
                stringList.add("Valid moves");
                stringList.add("White P on a3: [a4]");
                assertOutputs();
                System.out.println("Test case " + caseNumber + " PASSed!");

                assertContinueProgram();

                // Case 5: Single Knight on board, at d5.
                caseNumber = 5;
                readsPrompt("\nEnter number of pieces: ");
                writer.println("1");
                writer.flush();
                assertOneChessPieceConfig(1, 'B', 'N', "d5");
                stringList.add("");
                stringList.add("Valid moves");
                stringList.add("Black N on d5: [b4, b6, c3, c7, e3, e7, f4, f6]");
                assertOutputs();
                System.out.println("Test case " + caseNumber + " PASSed!");

                assertContinueProgram();

                // Case 6: Single Knight on board, at g6.
                caseNumber = 6;
                readsPrompt("\nEnter number of pieces: ");
                writer.println("1");
                writer.flush();
                assertOneChessPieceConfig(1, 'B', 'N', "g6");
                stringList.add("");
                stringList.add("Valid moves");
                stringList.add("Black N on g6: [e5, e7, f4, f8, h4, h8]");
                assertOutputs();
                System.out.println("Test case " + caseNumber + " PASSed!");

                assertContinueProgram();

                // Case 7: Single Knight on board, at h1.
                caseNumber = 7;
                readsPrompt("\nEnter number of pieces: ");
                writer.println("1");
                writer.flush();
                assertOneChessPieceConfig(1, 'B', 'N', "h1");
                stringList.add("");
                stringList.add("Valid moves");
                stringList.add("Black N on h1: [f2, g3]");
                assertOutputs();
                System.out.println("Test case " + caseNumber + " PASSed!");

                assertContinueProgram();

                // Case 8: Single Bishop on board, at e3.
                caseNumber = 8;
                readsPrompt("\nEnter number of pieces: ");
                writer.println("1");
                writer.flush();
                assertOneChessPieceConfig(1, 'W', 'B', "e3");
                stringList.add("");
                stringList.add("Valid moves");
                stringList.add("White B on e3: [a7, b6, c1, c5, d2, d4, f2, f4, g1, g5, h6]");
                assertOutputs();
                System.out.println("Test case " + caseNumber + " PASSed!");

                assertContinueProgram();

                // Case 9: Single Bishop on board, at d8.
                caseNumber = 9;
                readsPrompt("\nEnter number of pieces: ");
                writer.println("1");
                writer.flush();
                assertOneChessPieceConfig(1, 'B', 'B', "d8");
                stringList.add("");
                stringList.add("Valid moves");
                stringList.add("Black B on d8: [a5, b6, c7, e7, f6, g5, h4]");
                assertOutputs();
                System.out.println("Test case " + caseNumber + " PASSed!");

                assertContinueProgram();

                // Case 10: Single Bishop on board, at a4.
                caseNumber = 10;
                readsPrompt("\nEnter number of pieces: ");
                writer.println("1");
                writer.flush();
                assertOneChessPieceConfig(1, 'W', 'B', "a4");
                stringList.add("");
                stringList.add("Valid moves");
                stringList.add("White B on a4: [b3, b5, c2, c6, d1, d7, e8]");
                assertOutputs();
                System.out.println("Test case " + caseNumber + " PASSed!");

                assertContinueProgram();

                // Case 11: Example 1
                caseNumber = 11;
                readsPrompt("\nEnter number of pieces: ");
                writer.println("2");
                writer.flush();
                assertOneChessPieceConfig(1, 'W', 'P', "e4");
                assertOneChessPieceConfig(2, 'B', 'N', "c5");
                stringList.add("");
                stringList.add("Valid moves");
                stringList.add("White P on e4: [e5]");
                stringList.add("Black N on c5: [a4, a6, b3, b7, d3, d7, e4, e6]");
                assertOutputs();
                System.out.println("Test case " + caseNumber + " PASSed!");

                assertContinueProgram();

                // Case 12: Example 2
                caseNumber = 12;
                readsPrompt("\nEnter number of pieces: ");
                writer.println("3");
                writer.flush();
                assertOneChessPieceConfig(1, 'B', 'P', "g7");
                assertOneChessPieceConfig(2, 'W', 'P', "h6");
                assertOneChessPieceConfig(3, 'B', 'P', "h7");
                stringList.add("");
                stringList.add("Valid moves");
                stringList.add("Black P on g7: [g5, g6, h6]");
                stringList.add("White P on h6: [g7]");
                stringList.add("Black P on h7: []");
                assertOutputs();
                System.out.println("Test case " + caseNumber + " PASSed!");

                assertContinueProgram();

                // Case 13: Example 3
                caseNumber = 13;
                readsPrompt("\nEnter number of pieces: ");
                writer.println("3");
                writer.flush();
                assertOneChessPieceConfig(1, 'W', 'P', "f7");
                assertOneChessPieceConfig(2, 'B', 'N', "e8");
                assertOneChessPieceConfig(3, 'B', 'P', "g7");
                stringList.add("");
                stringList.add("Valid moves");
                stringList.add("White P on f7: [e8, f8]");
                stringList.add("Black N on e8: [c7, d6, f6]");
                stringList.add("Black P on g7: [g5, g6]");
                assertOutputs();
                System.out.println("Test case " + caseNumber + " PASSed!");

                assertContinueProgram();

                // Case 14: Example 4
                caseNumber = 14;
                readsPrompt("\nEnter number of pieces: ");
                writer.println("3");
                writer.flush();
                assertOneChessPieceConfig(1, 'W', 'B', "c2");
                assertOneChessPieceConfig(2, 'B', 'P', "d3");
                assertOneChessPieceConfig(3, 'B', 'N', "b4");
                stringList.add("");
                stringList.add("Valid moves");
                stringList.add("White B on c2: [a4, b1, b3, d1, d3]");
                stringList.add("Black P on d3: [c2, d2]");
                stringList.add("Black N on b4: [a2, a6, c2, c6, d5]");
                assertOutputs();
                System.out.println("Test case " + caseNumber + " PASSed!");

                assertContinueProgram();

                // Case 15: Example 5
                caseNumber = 15;
                readsPrompt("\nEnter number of pieces: ");
                writer.println("3");
                writer.flush();
                assertOneChessPieceConfig(1, 'W', 'B', "b1");
                assertOneChessPieceConfig(2, 'W', 'P', "d2");
                assertOneChessPieceConfig(3, 'W', 'N', "d3");
                stringList.add("");
                stringList.add("Valid moves");
                stringList.add("White B on b1: [a2, c2]");
                stringList.add("White P on d2: []");
                stringList.add("White N on d3: [b2, b4, c1, c5, e1, e5, f2, f4]");
                assertOutputs();
                System.out.println("Test case " + caseNumber + " PASSed!");

                assertTerminateProgram();

                // Finished
                System.out.println();
                System.out.println("All test cases PASSed!");

            } catch (AssertionError e) {
                System.err.println("Test case " + caseNumber + " FAILed!");
            } catch (IOException e) {
                System.err.println("StreamListener I/O Exception!");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.err.println("Tester program ends.");
            }
        }
    }
}
