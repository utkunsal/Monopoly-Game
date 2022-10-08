public class Railroad extends Property{

    public Railroad(int id, String name, int cost, String type) {
        super(id, name, cost, type);
    }

    @Override
    public int calculateRent(Player player, Player otherPlayer, int dice) {
        return 25*otherPlayer.getOwnedRailroadCount();
    }
}
