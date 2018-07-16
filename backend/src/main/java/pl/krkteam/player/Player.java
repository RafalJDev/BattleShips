package pl.krkteam.player;


public class Player {

    public String getName() {
        return name;
    }

    private final String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Player)) return false;
        return ((Player) obj).name.equals(this.name);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        for (char i : this.name.toCharArray()) {
            hash = hash * 31 + i;
        }
        return hash;
    }
}
