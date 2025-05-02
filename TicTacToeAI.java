public class TicTacToeAI {

    static char[][] board = new char[3][3];
    static char HUMAN;
    static char AI;

    // Main method to get the best move index for the AI
    public static int getBestMove(char[] currentBoard, char aiSymbol) {
        // Assign AI and Human symbols
        if (aiSymbol == 'X') {
            AI = 'X';
            HUMAN = 'O';
        } else {
            AI = 'O';
            HUMAN = 'X';
        }

        // Convert 1D board to 2D (handle '\0' properly)
        int index = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                char c = currentBoard[index++];
                board[i][j] = (c == '\0') ? ' ' : c;
            }

        // Get the best move using minimax
        int[] move = findBestMove();
        return move[0] * 3 + move[1]; // Return as 1D index
    }

    // Finds the best move for the AI using Minimax
    static int[] findBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int bestDepth = Integer.MAX_VALUE;
        int bestRow = -1;
        int bestCol = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = AI;
                    int[] result = minimax(false, 1);
                    board[i][j] = ' ';
                    int score = result[0];
                    int depth = result[1];

                    if (score > bestScore || (score == bestScore && depth < bestDepth)) {
                        bestScore = score;
                        bestDepth = depth;
                        bestRow = i;
                        bestCol = j;
                    }
                }
            }
        }

        return new int[] { bestRow, bestCol };
    }

    // Minimax recursive function
    static int[] minimax(boolean isMaximizing, int depth) {
        if (isWinner(AI))
            return new int[] { 1, depth };
        if (isWinner(HUMAN))
            return new int[] { -1, depth };
        if (isBoardFull())
            return new int[] { 0, depth };

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int bestDepth = Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = isMaximizing ? AI : HUMAN;
                    int[] result = minimax(!isMaximizing, depth + 1);
                    board[i][j] = ' ';
                    int score = result[0];
                    int resDepth = result[1];

                    if (isMaximizing) {
                        if (score > bestScore || (score == bestScore && resDepth < bestDepth)) {
                            bestScore = score;
                            bestDepth = resDepth;
                        }
                    } else {
                        if (score < bestScore || (score == bestScore && resDepth < bestDepth)) {
                            bestScore = score;
                            bestDepth = resDepth;
                        }
                    }
                }
            }
        }

        return new int[] { bestScore, bestDepth };
    }

    // Check if a given player has won
    static boolean isWinner(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player &&
                    board[i][1] == player &&
                    board[i][2] == player)
                return true;

            if (board[0][i] == player &&
                    board[1][i] == player &&
                    board[2][i] == player)
                return true;
        }

        return (board[0][0] == player &&
                board[1][1] == player &&
                board[2][2] == player)
                || (board[0][2] == player &&
                        board[1][1] == player &&
                        board[2][0] == player);
    }

    // Check if the board is completely filled
    static boolean isBoardFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return false;
        return true;
    }
}