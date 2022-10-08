public class Banker {
    private String name;
    private int money;
    private static Banker banker;

    private Banker(String name) {
        this.name = name;
        money = 100000;
    }

    public static void createBanker(String name){
        banker = new Banker(name);
    }

    public static Banker getBanker(){
        return banker;
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
}
