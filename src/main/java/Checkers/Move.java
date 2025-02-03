package Checkers;

// an object that contains info of movement and carry on captures 
public class Move {
    private Cell start;
    private Cell end;
    private CheckersPiece capturedPiece;

    public Move(Cell start, Cell end, CheckersPiece capturedPiece) {
        this.start = start;
        this.end = end;
        this.capturedPiece = capturedPiece;
    }

    public Cell getStart() {
        return start;
    }

    public Cell getEnd() {
        return end;
    }

    public CheckersPiece getCapturedPiece() {
        return capturedPiece;
    }
}
