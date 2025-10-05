public class TankDroid extends Droid {
    private static final int TANK_HEALTH = 180;
    private static final int TANK_DAMAGE = 15;
    private int armor = 5; // reduces damage taken

    public TankDroid(String name) {
        super(name, TANK_HEALTH, TANK_DAMAGE);
    }

    @Override
    public void takeDamage(int damage) {
        int reducedDamage = Math.max(damage - armor, 0);
        super.takeDamage(reducedDamage);
    }

    @Override
    public String attack(Droid target) {
        target.takeDamage(damage);
        return String.format("%s ударяє %s із важкою бронею на %d шкоди!", 
            name, target.getName(), damage);
    }

    @Override
    public String getType() {
        return "Танк";
    }

    @Override
    public String getColor() {
        return Colors.BRIGHT_BLUE;
    }

    public int getArmor() {
        return armor;
    }
}
