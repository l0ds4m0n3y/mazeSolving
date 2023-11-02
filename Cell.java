public class Cell {
    int row;
    int col;
    char character;
    
    Cell(int row, int col, char character){
        this.row = row;
        this.col = col;
        this.character = character;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getChar() {
        return character;
    }

    @Override
    public String toString() {
        return "Cell [row=" + row + ", col=" + col + ", character=" + character + "]";
    }

    @Override
    public boolean equals(Object c) {
        Cell ce = (Cell) c;
        return (ce.row == this.row && ce.col == this.col);
    }
}
