public class Cell implements Comparable<Cell>{
    static int endC;
    static int endR;

    int row;
    int col;
    Cell parent;
    
    Cell(int row, int col){
        this.row = row;
        this.col = col;
    }

    Cell(int row, int col, Cell parent){
        this.row = row;
        this.col = col;
        this.parent = parent;
    }

    public static void setEndStart(int endCol, int endRow){
        endR = endRow;
        endC = endCol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    private double distanceToCenter(){
        double col = Math.pow(this.col - endC, 2);
        double row = Math.pow(this.row - endR, 2);
        return Math.sqrt(col + row);
    }

    @Override
    public String toString() {
        return "Cell [row=" + row + ", col=" + col + ", parent=" + parent + "]";
    }

    @Override
    public boolean equals(Object c) {
        Cell ce = (Cell) c;
        return (ce.row == this.row && ce.col == this.col);
    }

    @Override
    public int compareTo(Cell other) {
        if(this.distanceToCenter() > other.distanceToCenter()) return 1;
        else if(this.distanceToCenter() < other.distanceToCenter()) return -1;
        return 0;
    }
}
