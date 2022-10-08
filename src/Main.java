public class Main {

    public static void main(String[] args) {
        Banker.createBanker("Banker");
        Board board = new Board();
        board.setPlayers(args[0]);
        board.startGame(args[0],"output.txt");
    }
}
