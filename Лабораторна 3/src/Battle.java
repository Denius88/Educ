import java.util.ArrayList;
import java.util.List;

public abstract class Battle {
    protected List<String> battleLog;
    
    public Battle() {
        this.battleLog = new ArrayList<>();
    }
    
    public abstract void start();
    
    public List<String> getBattleLog() {
        return battleLog;
    }
    
    protected void log(String message) {
        battleLog.add(message);
        System.out.println(message);
    }
    
    protected void clearLog() {
        battleLog.clear();
    }
}
