import java.util.ArrayList;

public class CommunityChest implements Square {

    private static ListJsonReader listJsonReader = new ListJsonReader();
    private static ArrayList<String> communityChestCards = listJsonReader.getCommunityChestArrayList();
    private static int index = 0;

    @Override
    public String action(Player player, Player otherPlayer, int dice, Banker banker) {
        String card = communityChestCards.get(index++);
        if (index >=communityChestCards.size())
            index -= communityChestCards.size();
        switch (card) {
            case "Advance to Go (Collect $200)":
                player.setLocation(0);
                break;

            case "Bank error in your favor - collect $75":
                player.updateMoney(75);
                banker.updateMoney(-75);
                break;

            case "Doctor's fees - Pay $50":
                player.updateMoney(-50);
                banker.updateMoney(50);
                break;

            case "It is your birthday Collect $10 from each player":
                player.updateMoney(10);
                otherPlayer.updateMoney(-10);
                break;

            case "Grand Opera Night - collect $50 from every player for opening night seats":
                player.updateMoney(50);
                otherPlayer.updateMoney(-50);
                break;

            case "Income Tax refund - collect $20":
                player.updateMoney(20);
                banker.updateMoney(-20);
                break;

            case "Life Insurance Matures - collect $100":
                player.updateMoney(100);
                banker.updateMoney(-100);
                break;

            case "Pay Hospital Fees of $100":
                player.updateMoney(-100);
                banker.updateMoney(100);
                break;

            case "Pay School Fees of $50":
                player.updateMoney(-50);
                banker.updateMoney(50);
                break;

            case "You inherit $100":
                player.updateMoney(100);
                banker.updateMoney(-100);
                break;

            case "From sale of stock you get $50":
                player.updateMoney(50);
                banker.updateMoney(-50);
                break;

        }

        return "\t"+player.getName()+" draw Community Chest - "+card;
    }
}
