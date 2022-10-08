import java.util.ArrayList;

public class Player {
    private String name;
    private String waitTime = "0";
    private int money, location;
    private Banker banker = Banker.getBanker();
    private ArrayList<Property> properties = new ArrayList<>();

    public Player(String name) {
        this.name = name;
        money = 15000;
    }

    public void updateMoney(int amount) {
        money += amount;
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public int getOwnedRailroadCount() {
        int count = 0;
        for (Property p : properties)
            if (p.getType().equals("RailRoad"))
                count++;
        return count;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void addProperty(Property property) {
        properties.add(property);
    }

    public int getLocation(){
        return location;
    }

    public void updateLocation(int dice){
        location += dice;
        if (location>=40) {
            location -= 40;
            money += 200;
            banker.updateMoney(-200);
        } else if (location<0) {
            location += 40;
        }

    }

    public void setLocation(int location) {
        if (location<this.location){
            money += 200;
            banker.updateMoney(-200);
        }
        this.location = location;
    }

    public void setWaitTime(String waitTime) {
        this.waitTime = waitTime;
    }

    public String getWaitTime() {
        return waitTime;
    }

}
