public class GoToJail implements Square{
    @Override
    public String action(Player player, Player otherPlayer, int dice, Banker banker) {
        player.setLocation(10);
        player.updateMoney(-200); // player does not take 200$ for passing go square
        banker.updateMoney(200);
        player.setWaitTime("3");
        return String.format("\t%s went to jail",player.getName());
    }
}
