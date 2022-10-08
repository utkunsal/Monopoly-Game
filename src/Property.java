public abstract class Property implements Square{
    private String name, type;
    private int id, cost;
    private boolean isBought = false;


    public Property(int id, String name, int cost, String type){
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.type = type;
    }

    public abstract int calculateRent(Player player, Player otherPlayer, int dice);

    public void buy(Player buyer, Banker banker){
        if (!isBought && buyer.getMoney()>=cost){
            isBought = true;
            buyer.updateMoney(-cost);
            buyer.addProperty(this);
            banker.updateMoney(cost);
        }
    }

    @Override
    public String action(Player player, Player otherPlayer, int dice, Banker banker){
        if (!isBought) {
            if (player.getMoney()>=cost) {
                buy(player, banker);
                return String.format("\t%s bought %s", player.getName(), name);
            } else {
                player.addProperty(this);
                player.updateMoney(-player.getMoney());
                return String.format("\t%s goes bankrupt", player.getName());
            }

        }
        else if (!player.getProperties().contains(this)) {
            int rent = calculateRent(player, otherPlayer, dice);
            if (player.getMoney()>=rent) {
                player.updateMoney(-rent);
                otherPlayer.updateMoney(rent);
                return String.format("\t%s paid rent for %s", player.getName(), name);
            } else {
                player.updateMoney(-player.getMoney());
                return String.format("\t%s goes bankrupt", player.getName());
            }
        } else
            return String.format("\t%s has %s",player.getName() ,name);
    }

    public int getCost() {
        return cost;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }
}
