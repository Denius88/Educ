public class RegeneratorDroid extends Droid {
    private static final int REGENERATOR_HEALTH = 110;
    private static final int REGENERATOR_DAMAGE = 12;
    private int regenPerTurn = 8;

    public RegeneratorDroid(String name) {
        super(name, REGENERATOR_HEALTH, REGENERATOR_DAMAGE);
    }

    @Override
    public String attack(Droid target) {
        // Регенерація на початку ходу
        String regenMsg = "";
        if (health < maxHealth && isAlive) {
            int oldHealth = health;
            heal(regenPerTurn);
            regenMsg = String.format("🔋 %s регенерує %d здоров'я (%d -> %d). ", 
                name, health - oldHealth, oldHealth, health);
        }

        // Атака
        target.takeDamage(damage);
        String attackMsg = String.format("%s атакує %s на %d шкоди", 
            name, target.getName(), damage);

        return regenMsg + attackMsg;
    }

    @Override
    public String getType() {
        return "Регенератор";
    }

    @Override
    public String getColor() {
        return Colors.GREEN;
    }
}
