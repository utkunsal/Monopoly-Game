import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Board {

    private PropertyJsonReader propertyJsonReader = new PropertyJsonReader();
    private Banker banker = Banker.getBanker();
    private ArrayList<Square> board;
    private Player[] players = new Player[2];

    public Board(){
        board = propertyJsonReader.getSquares();
        board.set(0, new Go());
        board.set(2, new CommunityChest());
        board.set(4, new Tax());
        board.set(7, new Chance());
        board.set(10, new Jail());
        board.set(17, new CommunityChest());
        board.set(20, new FreeParking());
        board.set(22, new Chance());
        board.set(30, new GoToJail());
        board.set(33, new CommunityChest());
        board.set(36, new Chance());
        board.set(38, new Tax());
    }

    private String play(Player player, Player otherPlayer, int dice){
        String message;
        if (!player.getWaitTime().equals("freeParking")) {
            if (player.getWaitTime().equals("0"))
                player.updateLocation(dice);
            message = board.get(player.getLocation()).action(player, otherPlayer, dice, banker);
            if (message.startsWith("movedToANewSquare")){
                message = message.substring(17) +" "+ board.get(player.getLocation()).action(player, otherPlayer, 0, banker).substring(1);
            }
        }
        else { //if (player.getWaitTime().equals("freeParking")){
            message = String.format("\t%s is in Free Parking", player.getName());
            player.setWaitTime("0");
        }
        return String.format("%s\t%d\t%d\t%d\t%d%s\n", player.getName(), dice, player.getLocation()+1,
                players[0].getMoney(), players[1].getMoney(), message);
    }

    public void setPlayers(String commandFile) {
        try {
            Scanner inp = new Scanner(new File(commandFile));
            int i = 0;
            while (inp.hasNextLine() && i<2){
                String command = inp.nextLine();
                if (!command.startsWith("show")){
                    String name = command.split(";")[0];
                    players[i++] = new Player(name);
                }
            }
            inp.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String show(){
        Player winner = players[0];
        if (players[1].getMoney()>players[0].getMoney())
            winner = players[1];
        return String.format("%s\t%d\thave: %s\n%s\t%d\thave: %s\n%s\t%d\nWinner\t%s\n"
                ,players[0].getName(),players[0].getMoney(),players[0].getProperties().toString().substring(1).replace("]","")
                ,players[1].getName(),players[1].getMoney(),players[1].getProperties().toString().substring(1).replace("]","")
                ,banker.getName(),banker.getMoney(),winner.getName());
    }

    public void startGame(String commandFile, String outputFile) {
        try {
            Scanner inp = new Scanner(new File(commandFile));
            Player player1 = players[0];
            Player player2 = players[1];
            FileWriter out = new FileWriter(outputFile);
            String s = "-----------------------------------------------------------------------------------------------------------\n";

            while (inp.hasNextLine()){
                String command = inp.nextLine();
                if (command.startsWith("show")){
                    out.write(s);
                    out.write(show());
                    out.write(s);
                } else {

                    String name = command.split(";")[0];
                    Player player = null, otherPlayer = null;
                    for(Player p : players){
                        if (p.getName().equals(name))
                            player = p;
                        else
                            otherPlayer = p;
                    }
                    out.write(play(player, otherPlayer, Integer.parseInt(command.split(";")[1])));
                }
                if (player1.getMoney()<=0 || player2.getMoney()<=0) {
                    out.write(s);
                    out.write(show());
                    out.write(s);
                    break;
                } else if (!inp.hasNextLine()) {
                    out.write(s);
                    out.write(show());
                    out.write(s);
                }

            }
            inp.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
