public class MedicDroid extends Droid {
    private static final int MEDIC_HEALTH = 100;
    private static final int MEDIC_DAMAGE = 10;
    private int healPower = 15;
    private int selfHealChance = 30; // 30% chance to heal after attacking

    public MedicDroid(String name) {
        super(name, MEDIC_HEALTH, MEDIC_DAMAGE);
    }

    @Override
    public String attack(Droid target) {
        target.takeDamage(damage);
        String result = String.format("%s атакує %s на %d шкоди!", 
            name, target.getName(), damage);
        
        // Self-heal ability
        if (Math.random() * 100 < selfHealChance && health < maxHealth) {
            heal(healPower);
            result += String.format(" %s лікує себе на %d здоров'я!", name, healPower);
        }
        
        return result;
    }

    @Override
    public String getType() {
        return "Медик";
    }

    @Override
    public String getColor() {
        return Colors.BRIGHT_CYAN;
    }

    public int getHealPower() {
        return healPower;
    }
}
