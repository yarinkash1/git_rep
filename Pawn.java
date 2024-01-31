public class Pawn extends ConcretePiece {
    private Player p;
    private int total_kills;

    public Pawn(Player p,String name, Position position)
    {
        super(p,name,position);
        this.total_kills=0;
    }
    public int totalKills() {
        return total_kills;
    }

    public void addKill() {
        this.total_kills+=1;
    }
    public void reset_kills()
    {
        this.total_kills=0;
    }



}
