public class FreeParking implements Square{
    @Override
    public String action(Player player, Player otherPlayer, int dice, Banker banker) {
        player.setWaitTime("freeParking");
        return String.format("\t%s is in Free Parking",player.getName());
    }
}
