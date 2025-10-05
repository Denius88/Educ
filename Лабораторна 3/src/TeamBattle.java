import java.util.ArrayList;
import java.util.List;

public class TeamBattle extends Battle {
    private List<Droid> team1;
    private List<Droid> team2;
    private int roundNumber;

    public TeamBattle(List<Droid> team1, List<Droid> team2) {
        super();
        this.team1 = new ArrayList<>(team1);
        this.team2 = new ArrayList<>(team2);
        this.roundNumber = 0;
    }

    @Override
    public void start() {
        clearLog();
        log(Colors.BOLD + Colors.BRIGHT_PURPLE + "\n⚔️⚔️  === КОМАНДНИЙ БІЙ === ⚔️⚔️" + Colors.RESET);
        log("\n" + Colors.BOLD + Colors.BLUE + "🛡️  Команда 1" + Colors.RESET);
        for (Droid d : team1) {
            log("  " + d.toString());
        }
        log("\n" + Colors.BOLD + Colors.YELLOW + "      ⚡ ПРОТи ⚡" + Colors.RESET);
        log("\n" + Colors.BOLD + Colors.RED + "🛡️  Команда 2" + Colors.RESET);
        for (Droid d : team2) {
            log("  " + d.toString());
        }
        log("\n" + Colors.BRIGHT_PURPLE + "=====================" + Colors.RESET + "\n");

        while (hasAliveDroids(team1) && hasAliveDroids(team2)) {
            roundNumber++;
            log(Colors.BOLD + Colors.PURPLE + "\n➤ Раунд " + roundNumber + Colors.RESET);
            log(Colors.PURPLE + "──────────" + Colors.RESET);
            
            // Team 1 attacks
            for (Droid attacker : team1) {
                if (attacker.isAlive()) {
                    Droid target = getRandomAliveDroid(team2);
                    if (target != null) {
                        String attackResult = attacker.attack(target);
                        log(attackResult);
                        log("   " + target.getName() + " " + target.getHealthBar() + " " + target.getHealth() + "/" + target.getMaxHealth());
                        
                        if (!target.isAlive()) {
                            log(Colors.RED + "  ☠️  " + target.getName() + " переможений!" + Colors.RESET);
                        }
                    }
                }
            }
            
            // Check if team2 is defeated
            if (!hasAliveDroids(team2)) {
                log(Colors.BOLD + Colors.GREEN + "\n\n🏆 ПЕРЕМОЖЕЦЬ: Команда 1! 🏆" + Colors.RESET);
                break;
            }
            
            log("");
            
            // Team 2 attacks
            for (Droid attacker : team2) {
                if (attacker.isAlive()) {
                    Droid target = getRandomAliveDroid(team1);
                    if (target != null) {
                        String attackResult = attacker.attack(target);
                        log(attackResult);
                        log("   " + target.getName() + " " + target.getHealthBar() + " " + target.getHealth() + "/" + target.getMaxHealth());
                        
                        if (!target.isAlive()) {
                            log(Colors.RED + "  ☠️  " + target.getName() + " переможений!" + Colors.RESET);
                        }
                    }
                }
            }
            
            // Check if team1 is defeated
            if (!hasAliveDroids(team1)) {
                log(Colors.BOLD + Colors.GREEN + "\n\n🏆 ПЕРЕМОЖЕЦЬ: Команда 2! 🏆" + Colors.RESET);
                break;
            }
            
            log("");
        }
    }

    private boolean hasAliveDroids(List<Droid> team) {
        for (Droid d : team) {
            if (d.isAlive()) {
                return true;
            }
        }
        return false;
    }

    private Droid getRandomAliveDroid(List<Droid> team) {
        List<Droid> aliveDroids = new ArrayList<>();
        for (Droid d : team) {
            if (d.isAlive()) {
                aliveDroids.add(d);
            }
        }
        
        if (aliveDroids.isEmpty()) {
            return null;
        }
        
        int randomIndex = (int)(Math.random() * aliveDroids.size());
        return aliveDroids.get(randomIndex);
    }

    public List<Droid> getTeam1() {
        return team1;
    }

    public List<Droid> getTeam2() {
        return team2;
    }
}
