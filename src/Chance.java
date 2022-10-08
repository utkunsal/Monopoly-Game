import java.util.ArrayList;

public class Chance implements Square {

    private static ListJsonReader listJsonReader = new ListJsonReader();
    private static ArrayList<String> chanceCards = listJsonReader.getChanceArrayList();
    private static int index = 0;

    @Override
    public String action(Player player, Player otherPlayer, int dice, Banker banker) {
        String card = chanceCards.get(index++);
        if (index >=chanceCards.size())
            index -= chanceCards.size();
        switch (card) {
            case "Advance to Go (Collect $200)":
                player.setLocation(0);
                break;
            case "Advance to Leicester Square":
                player.setLocation(26);
                return "movedToANewSquare\t"+player.getName()+" draw "+card;
            case "Go back 3 spaces":
                player.updateLocation(-3);
                return "movedToANewSquare\t"+player.getName()+" draw "+card;
            case "Pay poor tax of $15":
                player.updateMoney(-15);
                banker.updateMoney(15);
                break;
            case "Your building loan matures - collect $150":
                player.updateMoney(150);
                banker.updateMoney(-150);
                break;
            case "You have won a crossword competition - collect $100 ":
                player.updateMoney(100);
                banker.updateMoney(-100);
                break;
        }

        return "\t"+player.getName()+" draw "+card;
    }



}
