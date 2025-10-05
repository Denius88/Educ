public class AssaultDroid extends Droid {
    private static final int ASSAULT_HEALTH = 120;
    private static final int ASSAULT_DAMAGE = 25;
    private int criticalChance = 20; // 20% chance

    public AssaultDroid(String name) {
        super(name, ASSAULT_HEALTH, ASSAULT_DAMAGE);
    }

    @Override
    public String attack(Droid target) {
        int actualDamage = damage;
        boolean isCritical = Math.random() * 100 < criticalChance;
        
        if (isCritical) {
            actualDamage = (int)(damage * 1.5);
            target.takeDamage(actualDamage);
            return String.format("%s наносить КРИТИЧНИЙ УДАР по %s на %d шкоди!", 
                name, target.getName(), actualDamage);
        } else {
            target.takeDamage(actualDamage);
            return String.format("%s атакує %s на %d шкоди!", 
                name, target.getName(), actualDamage);
        }
    }

    @Override
    public String getType() {
        return "Штурмовик";
    }

    @Override
    public String getColor() {
        return Colors.BRIGHT_YELLOW;
    }
}
