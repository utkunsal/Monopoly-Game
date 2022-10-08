public class Company extends Property{

    public Company(int id, String name, int cost, String type) {
        super(id, name, cost, type);
    }

    @Override
    public int calculateRent(Player player, Player otherPlayer, int dice) {
        return 4*dice;
    }
}
