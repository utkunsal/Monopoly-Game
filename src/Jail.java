public class Jail implements Square{
    @Override
    public String action(Player player, Player otherPlayer, int dice, Banker banker) {
        if (player.getWaitTime().equals("0")) {
            player.setWaitTime("3");
            return String.format("\t%s went to jail", player.getName());
        }
        else if (player.getWaitTime().equals("3")) {
            player.setWaitTime("2");
            return String.format("\t%s in jail (count=%d)", player.getName(), 1);
        }
        else if (player.getWaitTime().equals("2")) {
            player.setWaitTime("1");
            return String.format("\t%s in jail (count=%d)", player.getName(), 2);
        }
        else if (player.getWaitTime().equals("1")) {
            player.setWaitTime("0");
            return String.format("\t%s in jail (count=%d)", player.getName(), 3);
        }
        return "";
    }
}
