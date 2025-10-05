public class SniperDroid extends Droid {
    private static final int SNIPER_HEALTH = 80;
    private static final int SNIPER_DAMAGE = 35;
    private int accuracy = 70; // 70% chance to hit

    public SniperDroid(String name) {
        super(name, SNIPER_HEALTH, SNIPER_DAMAGE);
    }

    @Override
    public String attack(Droid target) {
        boolean hit = Math.random() * 100 < accuracy;
        
        if (hit) {
            target.takeDamage(damage);
            return String.format("%s влучає в %s на %d шкоди!", 
                name, target.getName(), damage);
        } else {
            return String.format("%s промахується по %s!", name, target.getName());
        }
    }

    @Override
    public String getType() {
        return "Снайпер";
    }

    @Override
    public String getColor() {
        return Colors.BRIGHT_RED;
    }

    public int getAccuracy() {
        return accuracy;
    }
}
