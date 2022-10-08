public class Land extends Property{

    public Land(int id, String name, int cost, String type) {
        super(id, name, cost, type);
    }

    @Override
    public int calculateRent(Player player, Player otherPlayer, int dice) {
        if (super.getCost()<=2000)
            return super.getCost()*40/100;
        else if (super.getCost()<=3000)
            return super.getCost()*30/100;
        else //(super.getCost()<=4000)
            return super.getCost()*35/100;
    }
}
