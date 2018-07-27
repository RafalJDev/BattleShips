package pl.krkteam.battleships.common.domain.player;


public class Player {

    private final String playerName;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Player)) return false;
        return ((Player) obj).playerName.equals(this.playerName);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        for (char i : this.playerName.toCharArray()) {
            hash = hash * 31 + i;
        }
        return hash;
    }
}
