public class Cell {
    int row;
    int col;
    char character;
    
    Cell(int col, int row, char character){
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
    
}
