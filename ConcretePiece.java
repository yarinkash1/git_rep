import java.util.ArrayList;

public abstract class ConcretePiece implements Piece {

    private Player player_piece;
    private int total_steps;


    private ArrayList<Position> positions;

    private String name;





    public ConcretePiece(Player player_piece, String name, Position position)
    {
        this.player_piece=player_piece;
        this.name = name;
        total_steps=0;

        this.positions = new ArrayList<>();
        this.positions.add(position);


    }



    public void addSteps(int x)
    {
        this.total_steps=this.total_steps+x;
    }



    public int totalSteps()
    {
        return this.total_steps;
    }



    public void addPosition(Position p)
    {
        positions.add(p);
    }

    public String getName() {
        return name;
    }

    @Override
    public Player getOwner() {
        return player_piece;
    }

    @Override
    public String getType() {
        if (this instanceof Pawn)
        {
            if (getOwner().isPlayerOne())
            {
                return "♙";
            }
            else
            {
                return "♟";
            }
        }
        else
        {
            return "♔";
        }
    }

    public String getMoves() {
        return positions.toString();
    }

    public ArrayList<Position> getPositions() {
        return positions;
    }
}