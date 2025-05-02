public class MiniMax {
    public static int findBestMove(char[] board) {
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 9; i++) {
            if (board[i] == '\0')
                return i;
        }

        return 8;
    }
}