import java.util.ArrayList;
import java.util.Stack;

public class GameLogic implements PlayableLogic {
    //board size is final in order for the board size to be unchangeable.
    private final int board_size = 11;

    //game board will be treated as a matrix of a 2-dimensional array
    private final Piece[][] GameBoard;

    //the matrix that represent the moves will be in stack in order to make an undo button
    private Stack<Piece[][]> gameMoves;
    private ConcretePlayer firstPlayer;
    private ConcretePlayer secondPlayer;
    private boolean gameFinished = false;

    //will help us decide which player got this turn
    private int numOfMoves;








    //constructor
    public GameLogic() {
        this.firstPlayer = new ConcretePlayer(true);
        this.secondPlayer = new ConcretePlayer(false);
        this.GameBoard = new Piece[board_size][board_size];
        this.gameMoves = new Stack<Piece[][]>();
        this.numOfMoves = 0;

        init();
    }




    //helper method to initialize the board or reset it
    public void init() {

        gameFinished=false;
        this.numOfMoves = 0;
        for (int rows = 0; rows < board_size; rows++) {
            for (int columns = 0; columns < board_size; columns++) {
                GameBoard[rows][columns] = null;
            }
        }

        //Declaring the pawns and the king

        Pawn D1 = new Pawn(firstPlayer, "D1", new Position(5, 3));
        Pawn D2 = new Pawn(firstPlayer, "D2", new Position(4, 4));
        Pawn D3 = new Pawn(firstPlayer, "D3", new Position(5, 4));
        Pawn D4 = new Pawn(firstPlayer, "D4", new Position(6, 4));
        Pawn D5 = new Pawn(firstPlayer, "D5", new Position(3, 5));
        Pawn D6 = new Pawn(firstPlayer, "D6", new Position(4, 5));
        Pawn D8 = new Pawn(firstPlayer, "D8", new Position(6, 5));
        Pawn D9 = new Pawn(firstPlayer, "D9", new Position(7, 5));
        Pawn D10 = new Pawn(firstPlayer, "D10", new Position(4, 6));
        Pawn D11 = new Pawn(firstPlayer, "D11", new Position(5, 6));
        Pawn D12 = new Pawn(firstPlayer, "D12", new Position(6, 6));
        Pawn D13 = new Pawn(firstPlayer, "D13", new Position(5, 7));
        King K7 = new King(firstPlayer, "K7", new Position(5, 5));

        firstPlayer.addPiece(D1);
        firstPlayer.addPiece(D2);
        firstPlayer.addPiece(D3);
        firstPlayer.addPiece(D4);
        firstPlayer.addPiece(D5);
        firstPlayer.addPiece(D6);
        firstPlayer.addPiece(D8);
        firstPlayer.addPiece(D9);
        firstPlayer.addPiece(D10);
        firstPlayer.addPiece(D11);
        firstPlayer.addPiece(D12);
        firstPlayer.addPiece(D13);
        firstPlayer.addPiece(K7);

        firstPlayer.addKIller(D1);
        firstPlayer.addKIller(D2);
        firstPlayer.addKIller(D3);
        firstPlayer.addKIller(D4);
        firstPlayer.addKIller(D5);
        firstPlayer.addKIller(D6);
        firstPlayer.addKIller(D8);
        firstPlayer.addKIller(D9);
        firstPlayer.addKIller(D10);
        firstPlayer.addKIller(D11);
        firstPlayer.addKIller(D12);
        firstPlayer.addKIller(D13);


        Pawn A1 = new Pawn(secondPlayer, "A1", new Position(3, 0));
        Pawn A2 = new Pawn(secondPlayer, "A2", new Position(4, 0));
        Pawn A3 = new Pawn(secondPlayer, "A3", new Position(5, 0));
        Pawn A4 = new Pawn(secondPlayer, "A4", new Position(6, 0));
        Pawn A5 = new Pawn(secondPlayer, "A5", new Position(7, 0));
        Pawn A6 = new Pawn(secondPlayer, "A6", new Position(5, 1));
        Pawn A7 = new Pawn(secondPlayer, "A7", new Position(0, 3));
        Pawn A8 = new Pawn(secondPlayer, "A8", new Position(10, 3));
        Pawn A9 = new Pawn(secondPlayer, "A9", new Position(0, 4));
        Pawn A10 = new Pawn(secondPlayer, "A10", new Position(10, 4));
        Pawn A11 = new Pawn(secondPlayer, "A11", new Position(0, 5));
        Pawn A12 = new Pawn(secondPlayer, "A12", new Position(1, 5));
        Pawn A13 = new Pawn(secondPlayer, "A13", new Position(9, 5));
        Pawn A14 = new Pawn(secondPlayer, "A14", new Position(10, 5));
        Pawn A15 = new Pawn(secondPlayer, "A15", new Position(0, 6));
        Pawn A16 = new Pawn(secondPlayer, "A16", new Position(10, 6));
        Pawn A17 = new Pawn(secondPlayer, "A17", new Position(0, 7));
        Pawn A18 = new Pawn(secondPlayer, "A18", new Position(10, 7));
        Pawn A19 = new Pawn(secondPlayer, "A19", new Position(5, 9));
        Pawn A20 = new Pawn(secondPlayer, "A20", new Position(3, 10));
        Pawn A21 = new Pawn(secondPlayer, "A21", new Position(4, 10));
        Pawn A22 = new Pawn(secondPlayer, "A22", new Position(5, 10));
        Pawn A23 = new Pawn(secondPlayer, "A23", new Position(6, 10));
        Pawn A24 = new Pawn(secondPlayer, "A24", new Position(7, 10));

        secondPlayer.addPiece(A1);
        secondPlayer.addPiece(A2);
        secondPlayer.addPiece(A3);
        secondPlayer.addPiece(A4);
        secondPlayer.addPiece(A5);
        secondPlayer.addPiece(A6);
        secondPlayer.addPiece(A7);
        secondPlayer.addPiece(A8);
        secondPlayer.addPiece(A9);
        secondPlayer.addPiece(A10);
        secondPlayer.addPiece(A11);
        secondPlayer.addPiece(A12);
        secondPlayer.addPiece(A13);
        secondPlayer.addPiece(A14);
        secondPlayer.addPiece(A15);
        secondPlayer.addPiece(A16);
        secondPlayer.addPiece(A17);
        secondPlayer.addPiece(A18);
        secondPlayer.addPiece(A19);
        secondPlayer.addPiece(A20);
        secondPlayer.addPiece(A21);
        secondPlayer.addPiece(A22);
        secondPlayer.addPiece(A23);
        secondPlayer.addPiece(A24);

        secondPlayer.addKIller(A1);
        secondPlayer.addKIller(A2);
        secondPlayer.addKIller(A3);
        secondPlayer.addKIller(A4);
        secondPlayer.addKIller(A5);
        secondPlayer.addKIller(A6);
        secondPlayer.addKIller(A7);
        secondPlayer.addKIller(A8);
        secondPlayer.addKIller(A9);
        secondPlayer.addKIller(A10);
        secondPlayer.addKIller(A11);
        secondPlayer.addKIller(A12);
        secondPlayer.addKIller(A13);
        secondPlayer.addKIller(A14);
        secondPlayer.addKIller(A15);
        secondPlayer.addKIller(A16);
        secondPlayer.addKIller(A17);
        secondPlayer.addKIller(A18);
        secondPlayer.addKIller(A19);
        secondPlayer.addKIller(A20);
        secondPlayer.addKIller(A21);
        secondPlayer.addKIller(A22);
        secondPlayer.addKIller(A23);
        secondPlayer.addKIller(A24);


        //the following code is the positions of the pieces of the game according to Hnefatafl's rules.

        //attackers:

        for (int i = 0; i < secondPlayer.getPieces().size(); i++) {
            ConcretePiece p = secondPlayer.getPieces().get(i);
            int x = p.getPositions().get(0).getX();
            int y = p.getPositions().get(0).getY();
            GameBoard[x][y] = p;



                ((Pawn) p).reset_kills();




        }

        //Defenders and the king

        for (int i = 0; i < firstPlayer.getPieces().size(); i++) {
            ConcretePiece p = firstPlayer.getPieces().get(i);
            int x = p.getPositions().get(0).getX();
            int y = p.getPositions().get(0).getY();
            GameBoard[x][y] = p;
            // Check if p is an instance of Pawn before casting
            if (p instanceof Pawn) {
                ((Pawn) p).reset_kills();
            }
        }


    }

    @Override
    public boolean move(Position a, Position b) {
        //check if the position is occupied
        if (GameBoard[b.getX()][b.getY()] != null) {
            return false;
        }
        //player moves to the same spot he is in(illegal move)
        if (Math.abs(b.getX() - a.getX()) == 0 && (Math.abs(b.getY() - a.getY()) == 0)) {
            return false;
        }
        //player wants to move diagonally(illegal move)
        if (Math.abs(b.getX() - a.getX()) != 0 && (Math.abs(b.getY() - a.getY()) != 0)) {
            return false;
        }
        //now we are checking if we go over a piece in the line we want to travel along:
        //if we are in the x's line the difference between the a and b positions of y will be zero and vice versa

        //here we travel along the y-axis:
        if (Math.abs(b.getX() - a.getX()) == 0) {
            int min_y = Math.min(b.getY(), a.getY());
            int max_y = Math.max(b.getY(), a.getY());


            for (int start = min_y + 1; start < max_y; start++) {
                if (GameBoard[b.getX()][start] != null) {
                    return false;
                }
            }
        }

        //here we travel along the x-axis:
        if (Math.abs(b.getY() - a.getY()) == 0) {
            int min_x = Math.min(b.getX(), a.getX());
            int max_x = Math.max(b.getX(), a.getX());


            for (int start = min_x + 1; start < max_x; start++) {
                if (GameBoard[start][b.getY()] != null) {
                    return false;
                }
            }
        }

        if (GameBoard[a.getX()][a.getY()] instanceof King == true) {
            //make sure it's player 1 turn
            if (isSecondPlayerTurn() == true)
                return false;
            //add positions and steps to the fields
            ((King) GameBoard[a.getX()][a.getY()]).addSteps(Math.abs(b.getX() - a.getX()) + Math.abs(b.getY() - a.getY()));
            ((King) GameBoard[a.getX()][a.getY()]).addPosition(b);
            this.numOfMoves++;

            //make the move
            GameBoard[b.getX()][b.getY()] = GameBoard[a.getX()][a.getY()];
            GameBoard[a.getX()][a.getY()] = null;
            checkWin();
        } else {
            //check if the pawn wants to move to the extreme edges of the board and if so deny it of course.
            if ((b.getX() == 10 && b.getY() == 10) || (b.getX() == 10 && b.getY() == 0) || (b.getX() == 0 && b.getY() == 10) || (b.getX() == 0 && b.getY() == 0)) {
                return false;
            }

            Pawn test_pawn = (Pawn) GameBoard[a.getX()][a.getY()];
            Player owner = test_pawn.getOwner();
            //here we check if the pawn is an attacker one
            if (owner.isPlayerOne() == false) {

                //make sure it's player 2 turn
                if (isSecondPlayerTurn() == false) {
                    return false;
                }

                //add positions and steps to the fields
                ((Pawn) GameBoard[a.getX()][a.getY()]).addSteps(Math.abs(b.getX() - a.getX()) + Math.abs(b.getY() - a.getY()));
                ((Pawn) GameBoard[a.getX()][a.getY()]).addPosition(b);
                this.numOfMoves++;

                //make the move
                GameBoard[b.getX()][b.getY()] = GameBoard[a.getX()][a.getY()];
                GameBoard[a.getX()][a.getY()] = null;
                checkKill(b, true);
                checkWin();
            } else {
                //make sure its player 1 turn
                if (isSecondPlayerTurn() == true)
                    return false;

                //add positions and steps to the fields
                ((Pawn) GameBoard[a.getX()][a.getY()]).addSteps(Math.abs(b.getX() - a.getX()) + Math.abs(b.getY() - a.getY()));
                ((Pawn) GameBoard[a.getX()][a.getY()]).addPosition(b);
                this.numOfMoves++;

                //make the move
                GameBoard[b.getX()][b.getY()] = GameBoard[a.getX()][a.getY()];
                GameBoard[a.getX()][a.getY()] = null;
                checkKill(b, false);
                checkWin();


            }
        }


        return true;
    }

    public void checkKill(Position pos, boolean isAttacker) {
        int pos_x = pos.getX();
        int pos_y = pos.getY();

        //end cases attacker:

        //super super end cases attacker:

        if(isAttacker==true) {
            //top left corner
            if(pos_x==0&&pos_y==2)
            {
                if (GameBoard[pos_x][pos_y - 1] instanceof Pawn) {
                    Pawn p1_up = (Pawn) GameBoard[pos_x][pos_y - 1];
                    Player owner1_up = p1_up.getOwner();
                    boolean isDefense_up = owner1_up.isPlayerOne();

                    if (isDefense_up ) {
                        ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                        GameBoard[pos_x][pos_y - 1] = null;
                    }
                }
            }
            if(pos_x==2&&pos_y==0)
            {
                if (GameBoard[pos_x - 1][pos_y] instanceof Pawn) {


                    Pawn p1_left = (Pawn) GameBoard[pos_x - 1][pos_y];
                    Player owner1_left = p1_left.getOwner();
                    boolean isDefense_left = owner1_left.isPlayerOne();

                    if (isDefense_left ) {
                        ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                        GameBoard[pos_x - 1][pos_y] = null;
                    }
                }
            }
            //top right corner
            if(pos_x==10&&pos_y==2)
            {
                if (GameBoard[pos_x][pos_y - 1] instanceof Pawn ) {
                    Pawn p1_up = (Pawn) GameBoard[pos_x][pos_y - 1];
                    Player owner1_up = p1_up.getOwner();
                    boolean isDefense_up = owner1_up.isPlayerOne();



                    if (isDefense_up ) {
                        ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                        GameBoard[pos_x][pos_y - 1] = null;
                    }
                }
            }
            if(pos_x==8&&pos_y==0)
            {
                if (GameBoard[pos_x + 1][pos_y] instanceof Pawn ) {


                    Pawn p1_right = (Pawn) GameBoard[pos_x + 1][pos_y];
                    Player owner1_right = p1_right.getOwner();
                    boolean isDefense_right = owner1_right.isPlayerOne();



                    if (isDefense_right ) {
                        ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                        GameBoard[pos_x + 1][pos_y] = null;
                    }
                }
            }
            //bottom left corner
            if(pos_x==2&&pos_y==10)
            {
                if (GameBoard[pos_x - 1][pos_y] instanceof Pawn ) {


                    Pawn p1_left = (Pawn) GameBoard[pos_x - 1][pos_y];
                    Player owner1_left = p1_left.getOwner();
                    boolean isDefense_left = owner1_left.isPlayerOne();


                    if (isDefense_left ) {
                        ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                        GameBoard[pos_x - 1][pos_y] = null;
                    }
                }
            }
            if(pos_x==0&&pos_y==8)
            {
                if (GameBoard[pos_x][pos_y + 1] instanceof Pawn ) {
                    Pawn p1_down = (Pawn) GameBoard[pos_x][pos_y + 1];
                    Player owner1_down = p1_down.getOwner();
                    boolean isDefense_down = owner1_down.isPlayerOne();


                    if (isDefense_down ) {
                        ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                        GameBoard[pos_x][pos_y + 1] = null;
                    }
                }
            }
            //bottom right corner
            if(pos_x==8&&pos_y==10)
            {
                if (GameBoard[pos_x + 1][pos_y] instanceof Pawn ) {


                    Pawn p1_right = (Pawn) GameBoard[pos_x + 1][pos_y];
                    Player owner1_right = p1_right.getOwner();
                    boolean isDefense_right = owner1_right.isPlayerOne();


                    if (isDefense_right ) {
                        ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                        GameBoard[pos_x + 1][pos_y] = null;
                    }
                }
            }
            if(pos_x==10&&pos_y==8)
            {
                if (GameBoard[pos_x][pos_y + 1] instanceof Pawn ) {
                    Pawn p1_down = (Pawn) GameBoard[pos_x][pos_y + 1];
                    Player owner1_down = p1_down.getOwner();
                    boolean isDefense_down = owner1_down.isPlayerOne();



                    if (isDefense_down ) {
                        ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                        GameBoard[pos_x][pos_y + 1] = null;
                    }
                }
            }
        }


            else // defender super super end cases:
            {
                //top left corner
                if(pos_x==0&&pos_y==2)
                {
                    if (GameBoard[pos_x][pos_y - 1] instanceof Pawn) {
                        Pawn p1_up = (Pawn) GameBoard[pos_x][pos_y - 1];
                        Player owner1_up = p1_up.getOwner();
                        boolean isDefense_up = owner1_up.isPlayerOne();

                        if (isDefense_up==false ) {
                            ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                            GameBoard[pos_x][pos_y - 1] = null;
                        }
                    }
                }
                if(pos_x==2&&pos_y==0)
                {
                    if (GameBoard[pos_x - 1][pos_y] instanceof Pawn) {


                        Pawn p1_left = (Pawn) GameBoard[pos_x - 1][pos_y];
                        Player owner1_left = p1_left.getOwner();
                        boolean isDefense_left = owner1_left.isPlayerOne();

                        if (isDefense_left==false ) {
                            ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                            GameBoard[pos_x - 1][pos_y] = null;
                        }
                    }
                }
                //top right corner
                if(pos_x==10&&pos_y==2)
                {
                    if (GameBoard[pos_x][pos_y - 1] instanceof Pawn ) {
                        Pawn p1_up = (Pawn) GameBoard[pos_x][pos_y - 1];
                        Player owner1_up = p1_up.getOwner();
                        boolean isDefense_up = owner1_up.isPlayerOne();



                        if (isDefense_up==false ) {
                            ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                            GameBoard[pos_x][pos_y - 1] = null;
                        }
                    }
                }
                if(pos_x==8&&pos_y==0)
                {
                    if (GameBoard[pos_x + 1][pos_y] instanceof Pawn ) {


                        Pawn p1_right = (Pawn) GameBoard[pos_x + 1][pos_y];
                        Player owner1_right = p1_right.getOwner();
                        boolean isDefense_right = owner1_right.isPlayerOne();



                        if (isDefense_right==false ) {
                            ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                            GameBoard[pos_x + 1][pos_y] = null;
                        }
                    }
                }
                //bottom left corner
                if(pos_x==2&&pos_y==10)
                {
                    if (GameBoard[pos_x - 1][pos_y] instanceof Pawn ) {


                        Pawn p1_left = (Pawn) GameBoard[pos_x - 1][pos_y];
                        Player owner1_left = p1_left.getOwner();
                        boolean isDefense_left = owner1_left.isPlayerOne();


                        if (isDefense_left ==false) {
                            ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                            GameBoard[pos_x - 1][pos_y] = null;
                        }
                    }
                }
                if(pos_x==0&&pos_y==8)
                {
                    if (GameBoard[pos_x][pos_y + 1] instanceof Pawn ) {
                        Pawn p1_down = (Pawn) GameBoard[pos_x][pos_y + 1];
                        Player owner1_down = p1_down.getOwner();
                        boolean isDefense_down = owner1_down.isPlayerOne();


                        if (isDefense_down==false ) {
                            ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                            GameBoard[pos_x][pos_y + 1] = null;
                        }
                    }
                }
                //bottom right corner
                if(pos_x==8&&pos_y==10)
                {
                    if (GameBoard[pos_x + 1][pos_y] instanceof Pawn ) {


                        Pawn p1_right = (Pawn) GameBoard[pos_x + 1][pos_y];
                        Player owner1_right = p1_right.getOwner();
                        boolean isDefense_right = owner1_right.isPlayerOne();


                        if (isDefense_right==false ) {
                            ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                            GameBoard[pos_x + 1][pos_y] = null;
                        }
                    }
                }
                if(pos_x==10&&pos_y==8)
                {
                    if (GameBoard[pos_x][pos_y + 1] instanceof Pawn ) {
                        Pawn p1_down = (Pawn) GameBoard[pos_x][pos_y + 1];
                        Player owner1_down = p1_down.getOwner();
                        boolean isDefense_down = owner1_down.isPlayerOne();



                        if (isDefense_down==false ) {
                            ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                            GameBoard[pos_x][pos_y + 1] = null;
                        }
                    }
                }
            }



        //super end cases attacker:

        if(isAttacker==true) {
            if (pos_x == 0) {
                if (pos_y == 1) {
                    //check right:
                    checkRightAttacker(pos_x, pos_y);
                    //check downwards:
                    checkDownAttacker(pos_x, pos_y);

                } else if (pos_y == 9) {
                    //check right:
                    checkRightAttacker(pos_x, pos_y);
                    //check upwards
                    checkUpAttacker(pos_x, pos_y);
                }
            }

            if (pos_x == 10) {
                if (pos_y == 1) {
                    //check left:
                    checkLeftAttacker(pos_x, pos_y);
                }


                if (pos_y == 9) {
                    //check upwards
                    checkUpAttacker(pos_x, pos_y);
                    //check left:
                    checkLeftAttacker(pos_x, pos_y);
                } else {
                    checkDownAttacker(pos_x, pos_y);
                }

            }
            if (pos_y == 0) {
                if (pos_x == 1) {
                    //yamina velmata

                    //check right:
                    checkRightAttacker(pos_x, pos_y);
                    //check downwards:
                    checkDownAttacker(pos_x, pos_y);


                } else if (pos_x == 9) {
                    //smola velemta
                    //check left:
                    checkLeftAttacker(pos_x, pos_y);
                    //check downwards:
                    checkDownAttacker(pos_x, pos_y);
                }
            }
            if (pos_y == 10) {
                if (pos_x == 1) {
                    //lemala veyamina
                    //check upwards
                    checkUpAttacker(pos_x, pos_y);
                    //check right:
                    checkRightAttacker(pos_x, pos_y);

                } else if (pos_x == 9) {
                    //lemala vesmola
                    //check upwards
                    checkUpAttacker(pos_x, pos_y);
                    //check left:
                    checkLeftAttacker(pos_x, pos_y);
                }
            }
        }
        else {


            // super end cases defender:
            if (pos_x == 0) {
                if (pos_y == 1) {
                    //check right:
                    checkRightDefender(pos_x, pos_y);
                    //check downwards:
                    checkDownDefender(pos_x, pos_y);

                } else if (pos_y == 9) {
                    //check right:
                    checkRightDefender(pos_x, pos_y);
                    //check upwards
                    checkUpDefender(pos_x, pos_y);
                }
            }

            if (pos_x == 10) {
                if (pos_y == 1) {
                    //check left:
                    checkLeftDefender(pos_x, pos_y);
                }
                if (pos_y == 9) {
                    //check upwards
                    checkUpDefender(pos_x, pos_y);
                    //check left:
                    checkLeftDefender(pos_x, pos_y);
                } else {
                    checkDownDefender(pos_x, pos_y);
                }

            }
            if (pos_y == 0) {
                if (pos_x == 1) {
                    //yamina velmata

                    //check right:
                    checkRightDefender(pos_x, pos_y);
                    //check downwards:
                    checkDownDefender(pos_x, pos_y);


                } else if (pos_x == 9) {
                    //smola velemta
                    //check left:
                    checkLeftDefender(pos_x, pos_y);
                    //check downwards:
                    checkDownDefender(pos_x, pos_y);
                }
            }
            if (pos_y == 10) {
                if (pos_x == 1) {
                    //lemala veyamina
                    //check upwards
                    checkUpDefender(pos_x, pos_y);
                    //check right:
                    checkRightDefender(pos_x, pos_y);

                } else if (pos_x == 9) {
                    //lemala vesmola
                    //check upwards
                    checkUpDefender(pos_x, pos_y);
                    //check left:
                    checkLeftDefender(pos_x, pos_y);
                }
            }
        }

        //end cases attacker:

        if(isAttacker==true) {


            if ((pos_x == 10 || pos_x == 0) && pos_y != 9 && pos_y != 1) {

                //check for upwards and downwards:
                // upwards:
                checkUpAttacker(pos_x, pos_y);
                //downwards:
                checkDownAttacker(pos_x, pos_y);


            }
            if (pos_x == 0) {
                //check right:
                checkRightAttacker(pos_x, pos_y);
            }
            if (pos_x == 10) {
                //check left:
                checkLeftAttacker(pos_x, pos_y);
            }

            if ((pos_y == 0 || pos_y == 10) && pos_x != 1 && pos_x != 9) {
                //check left and right
                //check left:
                checkLeftAttacker(pos_x, pos_y);
                //check right:
                checkRightAttacker(pos_x, pos_y);
            }
            if (pos_y == 0) {
                //downwards:
                checkDownAttacker(pos_x, pos_y);
            }
            if (pos_y == 10) {
                // upwards:
                checkUpAttacker(pos_x, pos_y);
            }
        }
        else {
            //end cases defender:

            if (pos_x == 10 || pos_x == 0 && pos_y != 9 && pos_y != 1) {
                //check for upwards and downwards:
                // upwards:
                checkUpDefender(pos_x, pos_y);
                //downwards:
                checkDownDefender(pos_x, pos_y);
            }
            if (pos_x == 0) {
                //check right:
                checkRightDefender(pos_x, pos_y);
            }
            if (pos_x == 10) {
                //check left:
                checkLeftDefender(pos_x, pos_y);
            }

            if ((pos_y == 0 || pos_y == 10) && pos_x != 1 && pos_x != 9) {
                //check left and right
                //check left:
                checkLeftDefender(pos_x, pos_y);
                //check right:
                checkRightDefender(pos_x, pos_y);
            }
            if (pos_y == 0) {
                //downwards:
                checkDownDefender(pos_x, pos_y);
            }
            if (pos_y == 10) {
                // upwards:
                checkUpDefender(pos_x, pos_y);
            }
        }
        //here we check the non end-cases
        //Attacker:

        if ((pos_x >= 1 && pos_x <= 9) && (pos_y >= 1 && pos_y <= 9)) {
            if (isAttacker == true) {
                if (pos_x < 9) {
                    //check for right kill
                    checkRightAttacker(pos_x, pos_y);
                } else if (pos_x == 9) {
                    //check for right kill
                    if (pos_y != 0 && pos_y != 10) {


                        if (GameBoard[pos_x + 1][pos_y] instanceof Pawn) {
                            Pawn p1_right = (Pawn) GameBoard[pos_x + 1][pos_y];
                            Player owner1_right = p1_right.getOwner();
                            boolean isDefense_right = owner1_right.isPlayerOne();

                            if (isDefense_right == true) {
                                ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                                GameBoard[pos_x + 1][pos_y] = null;
                            }
                        }
                    }
                }


                if (pos_x > 1) {
                    //check for left kill
                    checkLeftAttacker(pos_x, pos_y);
                } else if (pos_x == 1) {
                    //check for left kill
                    if (pos_y != 0 && pos_y != 10) {


                        if (GameBoard[pos_x - 1][pos_y] instanceof Pawn) {
                            Pawn p1_left = (Pawn) GameBoard[pos_x - 1][pos_y];
                            Player owner1_left = p1_left.getOwner();
                            boolean isDefense_left = owner1_left.isPlayerOne();

                            if (isDefense_left == true) {
                                ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                                GameBoard[pos_x - 1][pos_y] = null;
                            }
                        }
                    }
                }

                //check for upwards kill

                if (pos_y > 1) {
                    checkUpAttacker(pos_x, pos_y);
                } else if (pos_y == 1) {
                    if (pos_x != 0 && pos_x != 10) {
                        if (GameBoard[pos_x][pos_y - 1] instanceof Pawn) {
                            Pawn p1_up = (Pawn) GameBoard[pos_x][pos_y - 1];
                            Player owner1_up = p1_up.getOwner();
                            boolean isDefense_up = owner1_up.isPlayerOne();


                            if (isDefense_up) {
                                ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                                GameBoard[pos_x][pos_y - 1] = null;
                            }
                        }
                    }

                }


                //check for downwards kill

                if (pos_y < 9) {
                    checkDownAttacker(pos_x, pos_y);
                } else if (pos_y == 9) {
                    if (pos_x != 0 && pos_x != 10) {
                        if (GameBoard[pos_x][pos_y + 1] instanceof Pawn) {
                            Pawn p1_down = (Pawn) GameBoard[pos_x][pos_y + 1];
                            Player owner1_down = p1_down.getOwner();
                            boolean isDefense_down = owner1_down.isPlayerOne();


                            if (isDefense_down) {
                                ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                                GameBoard[pos_x][pos_y + 1] = null;
                            }
                        }
                    }
                }

            }


            //isAttacker == false
            else {
                if (pos_x < 9) {
                    //check for right kill
                    checkRightDefender(pos_x, pos_y);
                } else if (pos_x == 9) {
                    //check for right kill
                    if (pos_y != 0 && pos_y != 10) {


                        if (GameBoard[pos_x + 1][pos_y] instanceof Pawn) {
                            Pawn p1_right = (Pawn) GameBoard[pos_x + 1][pos_y];
                            Player owner1_right = p1_right.getOwner();
                            boolean isDefense_right = owner1_right.isPlayerOne();

                            if (isDefense_right == false) {
                                ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                                GameBoard[pos_x + 1][pos_y] = null;
                            }
                        }
                    }
                }


                if (pos_x > 1) {
                    //check for left kill
                    checkLeftDefender(pos_x, pos_y);
                } else if (pos_x == 1) {
                    //check for left kill
                    if (pos_y != 0 && pos_y != 10) {


                        if (GameBoard[pos_x - 1][pos_y] instanceof Pawn) {
                            Pawn p1_left = (Pawn) GameBoard[pos_x - 1][pos_y];
                            Player owner1_left = p1_left.getOwner();
                            boolean isDefense_left = owner1_left.isPlayerOne();

                            if (isDefense_left == false) {
                                ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                                GameBoard[pos_x - 1][pos_y] = null;
                            }
                        }
                    }
                }

                //check for upwards kill

                if (pos_y > 1) {
                    checkUpDefender(pos_x, pos_y);
                } else if (pos_y == 1) {
                    if (pos_x != 0 && pos_x != 10) {
                        if (GameBoard[pos_x][pos_y - 1] instanceof Pawn) {
                            Pawn p1_up = (Pawn) GameBoard[pos_x][pos_y - 1];
                            Player owner1_up = p1_up.getOwner();
                            boolean isDefense_up = owner1_up.isPlayerOne();


                            if (isDefense_up == false) {
                                ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                                GameBoard[pos_x][pos_y - 1] = null;
                            }
                        }
                    }


                }


                //check for downwards kill

                if (pos_y < 9) {
                    checkDownDefender(pos_x, pos_y);
                } else if (pos_y == 9) {
                    if (pos_x != 0 && pos_x != 10) {
                        if (GameBoard[pos_x][pos_y + 1] instanceof Pawn) {
                            Pawn p1_down = (Pawn) GameBoard[pos_x][pos_y + 1];
                            Player owner1_down = p1_down.getOwner();
                            boolean isDefense_down = owner1_down.isPlayerOne();


                            if (isDefense_down == false) {
                                ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                                GameBoard[pos_x][pos_y + 1] = null;
                            }
                        }
                    }
                }
            }
        }


    }


    @Override
    public Piece getPieceAtPosition(Position position) {


        int x = position.getX();
        int y = position.getY();


        //check to see if the position is within the range of the board
        if (x >= 0 && x < board_size && y >= 0 && y < board_size) {
            return GameBoard[x][y];
        } else {
            return null;
        }
    }

    @Override
    public Player getFirstPlayer() {
        return this.firstPlayer;
    }


    @Override
    public Player getSecondPlayer() {
        return this.secondPlayer;
    }

    public void checkWin()
    {

        // we will check if all attack/defense pawns are dead OR if the king is surrounded by 4 attackers OR if the king manages to escape to the corner


        //check if king managed to escape to the corner
        if (GameBoard[0][0] instanceof King ||
                GameBoard[10][0] instanceof King ||
                GameBoard[0][10] instanceof King ||
                GameBoard[10][10] instanceof King) {
            firstPlayer.addWin();
            printGameFinished(firstPlayer, secondPlayer);

            gameFinished=true;

        }


        //check if all attack/defense pawns are dead
        int count1 = 0;
        int count2 = 0;
        for (int rows = 0; rows < board_size; rows++) {
            for (int cols = 0; cols < board_size; cols++) {
                if (GameBoard[rows][cols] instanceof Pawn) {
                    Pawn p = (Pawn) GameBoard[rows][cols];
                    Player owner = p.getOwner();
                    if (owner.isPlayerOne())
                        count1++;
                    else
                        count2++;

                }
            }
        }
        //if someone has no pawns on the bored so the game will end
        if (count1 == 0) {
            secondPlayer.addWin();
            printGameFinished(secondPlayer, firstPlayer);

            gameFinished=true;
        }
        if (count2 == 0) {
            firstPlayer.addWin();
            printGameFinished(firstPlayer, secondPlayer);

            gameFinished=true;
        }


        //check if king is surrounded by 4 attackers

        boolean king_trapped = false;
        for (int m = 0; m < board_size; m++) {
            for (int k = 0; k < board_size; k++) {
                if (GameBoard[m][k] instanceof King) {
                    king_trapped = checkIfKingIsTrapped(m, k);
                    break;
                }
            }
        }

        if (king_trapped == true) {
            secondPlayer.addWin();
            printGameFinished(secondPlayer, firstPlayer);

            gameFinished=true;
        }



    }

    @Override
    public boolean isGameFinished()
    {
        return gameFinished;
    }


    //helper method to check if the king is trapped, so we would not need to check the rest of the board since there is only one king
    private boolean checkIfKingIsTrapped(int x, int y) {
        boolean ans = false;
        boolean left_trapped = false;
        boolean upwards_trapped = false;
        boolean right_trapped = false;
        boolean downwards_trapped = false;

        //check right_side:

        //check if the array isn't out of bounds
        if (x + 1 < board_size) {
            //here we check if there is a pawn from the right side of the king
            if (GameBoard[x + 1][y] instanceof Pawn) {
                Pawn test_pawn = (Pawn) GameBoard[x + 1][y];
                Player owner = test_pawn.getOwner();
                //here we check if the pawn is an attacker one
                if (owner.isPlayerOne() == false) {
                    right_trapped = true;
                }
            }
        }
        //here we check if the king is next to the edge of the board from the right
        else if (x == board_size - 1) {
            right_trapped = true;
        }

        //check left_side:

        //check if the array isn't out of bounds
        if (x - 1 >= 0) {
            //here we check if there is a pawn from the left side of the king
            if (GameBoard[x - 1][y] instanceof Pawn) {
                Pawn test_pawn = (Pawn) GameBoard[x - 1][y];
                Player owner = test_pawn.getOwner();
                //here we check if the pawn is an attacker one
                if (owner.isPlayerOne() == false) {
                    left_trapped = true;
                }
            }
        }
        //here we check if the king is next to the edge of the board from the left
        else if (x == 0) {
            left_trapped = true;
        }

        //check upwards:

        //check if the array isn't out of bounds
        if (y - 1 >= 0) {
            //here we check if there is a pawn above the king
            if (GameBoard[x][y - 1] instanceof Pawn) {
                Pawn test_pawn = (Pawn) GameBoard[x][y - 1];
                Player owner = test_pawn.getOwner();
                //here we check if the pawn is an attacker one
                if (owner.isPlayerOne() == false) {
                    upwards_trapped = true;
                }
            }
        }
        //here we check if the king is in the edge from the top of the board
        else if (y == 0) {
            upwards_trapped = true;
        }


        //check downwards:

        //check if the array isn't out of bounds
        if (y + 1 < board_size) {
            //here we check if there is a pawn below the king
            if (GameBoard[x][y + 1] instanceof Pawn) {
                Pawn test_pawn = (Pawn) GameBoard[x][y + 1];
                Player owner = test_pawn.getOwner();
                //here we check if the pawn is an attacker one
                if (owner.isPlayerOne() == false) {
                    downwards_trapped = true;
                }
            }
        }
        //here we check if the king is in the edge from the bottom of the board
        else if (y == board_size - 1) {
            downwards_trapped = true;
        }


        if (right_trapped == true && left_trapped == true && upwards_trapped == true && downwards_trapped == true) {
            ans = true;
            return ans;
        }
        return ans;
    }

    //if number of moves is even it's player 2 turn
    @Override
    public boolean isSecondPlayerTurn() {
        return numOfMoves % 2 == 0;
    }

    @Override
    public void reset() {
        init();


    }

    @Override
    public void undoLastMove() {
        if (gameMoves.size() > 0)
            gameMoves.pop();
    }

    @Override
    public int getBoardSize() {
        return board_size;
    }


    //    ==================================== PRIVATE METHODS ====================================



    private void checkLeftDefender(int pos_x, int pos_y) {
        if (GameBoard[pos_x - 1][pos_y] instanceof Pawn && GameBoard[pos_x - 2][pos_y] instanceof Pawn) {


            Pawn p1_left = (Pawn) GameBoard[pos_x - 1][pos_y];
            Player owner1_left = p1_left.getOwner();
            boolean isDefense_left = owner1_left.isPlayerOne();

            Pawn p2_left = (Pawn) GameBoard[pos_x - 2][pos_y];
            Player owner2_left = p2_left.getOwner();
            boolean isNotDefense_left = owner2_left.isPlayerOne();

            if (isDefense_left == false && isNotDefense_left) {
                ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                GameBoard[pos_x - 1][pos_y] = null;
            }
        }
    }

    private void checkUpDefender(int pos_x, int pos_y) {
        if (GameBoard[pos_x][pos_y - 1] instanceof Pawn && GameBoard[pos_x][pos_y - 2] instanceof Pawn) {
            Pawn p1_up = (Pawn) GameBoard[pos_x][pos_y - 1];
            Player owner1_up = p1_up.getOwner();
            boolean isDefense_up = owner1_up.isPlayerOne();

            Pawn p2_up = (Pawn) GameBoard[pos_x][pos_y - 2];
            Player owner2_up = p2_up.getOwner();
            boolean isNotDefense_up = owner2_up.isPlayerOne();

            if (isDefense_up == false && isNotDefense_up) {
                ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                GameBoard[pos_x][pos_y - 1] = null;
            }
        }
    }

    private void checkDownDefender(int pos_x, int pos_y) {
        if (GameBoard[pos_x][pos_y + 1] instanceof Pawn && GameBoard[pos_x][pos_y + 2] instanceof Pawn) {
            Pawn p1_down = (Pawn) GameBoard[pos_x][pos_y + 1];
            Player owner1_down = p1_down.getOwner();
            boolean isDefense_down = owner1_down.isPlayerOne();

            Pawn p2_down = (Pawn) GameBoard[pos_x][pos_y + 2];
            Player owner2_down = p2_down.getOwner();
            boolean isNotDefense_down = owner2_down.isPlayerOne();

            if (isDefense_down == false && isNotDefense_down) {
                ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                GameBoard[pos_x][pos_y + 1] = null;
            }
        }
    }

    private void checkRightDefender(int pos_x, int pos_y) {
        if (GameBoard[pos_x + 1][pos_y] instanceof Pawn && GameBoard[pos_x + 2][pos_y] instanceof Pawn) {


            Pawn p1_right = (Pawn) GameBoard[pos_x + 1][pos_y];
            Player owner1_right = p1_right.getOwner();
            boolean isDefense_right = owner1_right.isPlayerOne();

            Pawn p2_right = (Pawn) GameBoard[pos_x + 2][pos_y];
            Player owner2_right = p2_right.getOwner();
            boolean isNotDefense_right = owner2_right.isPlayerOne();

            if (isDefense_right == false && isNotDefense_right) {
                ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                GameBoard[pos_x + 1][pos_y] = null;
            }
        }
    }

    private void checkLeftAttacker(int pos_x, int pos_y) {
        if (GameBoard[pos_x - 1][pos_y] instanceof Pawn && GameBoard[pos_x - 2][pos_y] instanceof Pawn) {


            Pawn p1_left = (Pawn) GameBoard[pos_x - 1][pos_y];
            Player owner1_left = p1_left.getOwner();
            boolean isDefense_left = owner1_left.isPlayerOne();

            Pawn p2_left = (Pawn) GameBoard[pos_x - 2][pos_y];
            Player owner2_left = p2_left.getOwner();
            boolean isNotDefense_left = owner2_left.isPlayerOne();

            if (isDefense_left && isNotDefense_left == false) {
                ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                GameBoard[pos_x - 1][pos_y] = null;
            }
        }
    }

    private void checkUpAttacker(int pos_x, int pos_y) {
        if (GameBoard[pos_x][pos_y - 1] instanceof Pawn && GameBoard[pos_x][pos_y - 2] instanceof Pawn) {
            Pawn p1_up = (Pawn) GameBoard[pos_x][pos_y - 1];
            Player owner1_up = p1_up.getOwner();
            boolean isDefense_up = owner1_up.isPlayerOne();

            Pawn p2_up = (Pawn) GameBoard[pos_x][pos_y - 2];
            Player owner2_up = p2_up.getOwner();
            boolean isNotDefense_up = owner2_up.isPlayerOne();

            if (isDefense_up && isNotDefense_up == false) {
                ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                GameBoard[pos_x][pos_y - 1] = null;
            }
        }
    }

    private void checkDownAttacker(int pos_x, int pos_y) {
        if (GameBoard[pos_x][pos_y + 1] instanceof Pawn && GameBoard[pos_x][pos_y + 2] instanceof Pawn) {
            Pawn p1_down = (Pawn) GameBoard[pos_x][pos_y + 1];
            Player owner1_down = p1_down.getOwner();
            boolean isDefense_down = owner1_down.isPlayerOne();

            Pawn p2_down = (Pawn) GameBoard[pos_x][pos_y + 2];
            Player owner2_down = p2_down.getOwner();
            boolean isNotDefense_down = owner2_down.isPlayerOne();

            if (isDefense_down && isNotDefense_down == false) {
                ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                GameBoard[pos_x][pos_y + 1] = null;
            }
        }
    }

    private void checkRightAttacker(int pos_x, int pos_y) {
        if (GameBoard[pos_x + 1][pos_y] instanceof Pawn && GameBoard[pos_x + 2][pos_y] instanceof Pawn) {


            Pawn p1_right = (Pawn) GameBoard[pos_x + 1][pos_y];
            Player owner1_right = p1_right.getOwner();
            boolean isDefense_right = owner1_right.isPlayerOne();

            Pawn p2_right = (Pawn) GameBoard[pos_x + 2][pos_y];
            Player owner2_right = p2_right.getOwner();
            boolean isNotDefense_right = owner2_right.isPlayerOne();

            if (isDefense_right && isNotDefense_right == false) {
                ((Pawn) GameBoard[pos_x][pos_y]).addKill();
                GameBoard[pos_x + 1][pos_y] = null;
            }
        }
    }

    private void printGameFinished(ConcretePlayer win, ConcretePlayer lose){
        ConcretePlayer.printMovesHistory(win, lose);
        System.out.println("***************************************************************************");
        ConcretePlayer.printKillsHistory(win, lose);
        System.out.println("***************************************************************************");
        ConcretePlayer.printSquaresHistory(win, lose);
        System.out.println("***************************************************************************");
        ConcretePlayer.printPositionsHistory(win, lose);
        System.out.println("***************************************************************************");

    }

}