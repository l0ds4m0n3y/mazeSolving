public class Cell {
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

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
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
}
