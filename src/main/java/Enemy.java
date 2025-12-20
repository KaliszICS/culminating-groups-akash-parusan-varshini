public class Enemy extends Character {
    private String name;

    public Enemy(String name, int health, int attack) {
        this.name = name;
        this.health = health;
        this.attack = attack;
    }

    @Override
    public void takeTurn() {
    }

    public String getName() {
        return name;
    }
}