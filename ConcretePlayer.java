import java.util.*;

public class ConcretePlayer implements Player {

    //False= Defender True= Attacker
    private boolean attOrDef;
    private int wins;

    // this variable hold the alive\killed pieces.
    private ArrayList<ConcretePiece> pieces;
    private ArrayList<Pawn> killers;

    public ConcretePlayer(boolean attOrDef) {
        this.attOrDef = attOrDef;
        this.wins = 0;
        pieces = new ArrayList<>();
        killers = new ArrayList<>();
    }

    @Override
    public boolean isPlayerOne() {
        return attOrDef;
    }

    @Override
    public int getWins() {
        return this.wins;
    }

    public void addWin() {
        this.wins += 1;
    }

    public void addPiece(ConcretePiece piece) {
        pieces.add(piece);
    }


    public ArrayList<ConcretePiece> getPieces() {
        return pieces;
    }



    public void addKIller(Pawn piece) {
        killers.add(piece);
    }



    public static class PieceMovesComparator implements Comparator<ConcretePiece> {

        @Override
        public int compare(ConcretePiece o1, ConcretePiece o2) {
            if (o1.getPositions().size() > o2.getPositions().size())
                return 1;
            else if (o1.getPositions().size() < o2.getPositions().size())
                return -1;
            else {
                // Remove the first letter: example -> (D12 > D7) -> (12>7)?
                if (Integer.parseInt(o1.getName().substring(1)) > Integer.parseInt(o2.getName().substring(1))) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
    }

    // A
    public static void printMovesHistory(ConcretePlayer win, ConcretePlayer lose) {
        win.pieces.sort(new PieceMovesComparator());
        lose.pieces.sort(new PieceMovesComparator());

        for (ConcretePiece piece : win.pieces) {
            if (piece.getPositions().size() > 1) {
                System.out.print(piece.getName() + ": " + piece.getMoves());
                System.out.println();
            }
        }
        for (ConcretePiece piece : lose.pieces) {
            if (piece.getPositions().size() > 1) {
                System.out.print(piece.getName() + ": " + piece.getMoves());
                System.out.println();
            }
        }
    }

    //B
    public static void printKillsHistory(ConcretePlayer win, ConcretePlayer lose) {
        ArrayList<Pawn> allKillers = new ArrayList<>(win.killers);
        allKillers.addAll(lose.killers);

        allKillers.sort((Pawn o1, Pawn o2) -> {
            int killsComparison = Integer.compare(o2.totalKills(), o1.totalKills());
            if (killsComparison != 0) {
                return killsComparison;
            }

            int pieceNumber1 = Integer.parseInt(o1.getName().substring(1));
            int pieceNumber2 = Integer.parseInt(o2.getName().substring(1));

            if (pieceNumber1 != pieceNumber2) {
                return Integer.compare(pieceNumber1, pieceNumber2);
            }

            // Compare by winner and loser if total kills and piece number are equal
            return o1.getOwner().equals("win") ? -1 : 1;
        });

        for (Pawn p : allKillers) {
            if(p.totalKills()>0)
            {
                System.out.print(p.getName() + ": " + p.totalKills() + " kills\n");
            }

        }
    }



    // C
    public static void printSquaresHistory(ConcretePlayer win, ConcretePlayer lose) {
        ArrayList<ConcretePiece> allPieces = new ArrayList<>();
        allPieces.addAll(win.pieces);
        allPieces.addAll(lose.pieces);

        allPieces.sort((ConcretePiece o1, ConcretePiece o2) -> {
            if (o1.totalSteps() == o2.totalSteps()) {
                int o1Number = Integer.parseInt(o1.getName().substring(1));
                int o2Number = Integer.parseInt(o2.getName().substring(1));

                if (o1Number == o2Number) {
                    return 0;
                }
                return o1Number > o2Number ? 1 : -1;
            }
            return o1.totalSteps() > o2.totalSteps() ? -1 : 1;
        });

        for (ConcretePiece p : allPieces) {
            if(p.totalSteps()>0) {
                System.out.print(p.getName() + ": " + p.totalSteps() + " squares\n");
            }

        }
    }


    //D


    public static void printPositionsHistory(ConcretePlayer win, ConcretePlayer lose) {
        Set<String> pieces_arr[][] = new HashSet[11][11];

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                pieces_arr[i][j] = new HashSet<>();
            }
        }

        // Combine and sort pieces from both win and lose players
        ArrayList<ConcretePiece> allPieces = new ArrayList<>(win.pieces);
        allPieces.addAll(lose.pieces);
        allPieces.sort(new PieceMovesComparator());

        for (ConcretePiece piece : allPieces) {
            if (!piece.getPositions().isEmpty()) {
                for (int i = 0; i < piece.getPositions().size(); i++) {
                    int x = piece.getPositions().get(i).getX();
                    int y = piece.getPositions().get(i).getY();
                    pieces_arr[x][y].add(piece.getName());
                }
            }
        }

        // Create a list to store entries (positions and sizes)
        List<Map.Entry<String, Set<String>>> positionSizes = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (pieces_arr[i][j].size() > 1) {
                    positionSizes.add(new AbstractMap.SimpleEntry<>("(" + i + ", " + j + ")", pieces_arr[i][j]));
                }
            }
        }
///////////////////////////////////////////////////help source: chat-gpt////////////////////////////////////////////////////////////////////
        // Sort the list in ascending order based on sizes, X, and Y
        Collections.sort(positionSizes, new Comparator<Map.Entry<String, Set<String>>>() {
            @Override
            public int compare(Map.Entry<String, Set<String>> entry1, Map.Entry<String, Set<String>> entry2) {
                int sizeComparison = Integer.compare(entry2.getValue().size(), entry1.getValue().size());

                if (sizeComparison == 0) {
                    // If sizes are the same, compare X values
                    int xComparison = Integer.compare(getXFromPosition(entry1.getKey()), getXFromPosition(entry2.getKey()));

                    if (xComparison == 0) {
                        // If X values are the same, compare Y values
                        return Integer.compare(getYFromPosition(entry1.getKey()), getYFromPosition(entry2.getKey()));
                    } else {
                        return xComparison;
                    }
                } else {
                    return sizeComparison;
                }
            }
            // Helper method to extract X value from position string
            private int getXFromPosition(String position) {
                // Extracting the X value from the position string in the format "(i, j)"
                String[] parts = position.substring(1, position.length() - 1).split(",");
                return Integer.parseInt(parts[0].trim());
            }




            // Helper method to extract Y value from position string
            private int getYFromPosition(String position) {
                // Extracting the Y value from the position string in the format "(i, j)"
                String[] parts = position.substring(1, position.length() - 1).split(",");
                return Integer.parseInt(parts[1].trim());
            }


        });

        // Print positions and their sizes
        for (Map.Entry<String, Set<String>> entry : positionSizes) {
            System.out.println(entry.getKey() + entry.getValue().size() + " pieces");
        }
    }
    ///////////////////////////////////////////////////help source: chat-gpt////////////////////////////////////////////////////////////////////

}