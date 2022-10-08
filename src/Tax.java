public class Tax implements Square{
    @Override
    public String action(Player player, Player otherPlayer, int dice, Banker banker) {

        player.updateMoney(-100);
        banker.updateMoney(+100);
        if (player.getMoney()>=0)
            return String.format("\t%s paid tax",player.getName());
        else {
            player.updateMoney(-player.getMoney());
            return String.format("\t%s goes bankrupt", player.getName());
        }
    }
}
