import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class QueueMazeSolver implements MazeSolver{
    private MazeGUI gui;

    QueueMazeSolver(){
        gui = new MazeGUI(this);
    }
    
    @Override
    public void solve(char[][] maze, int startR, int startC, int endR, int endC) {
        Boolean solvable = false;
        Queue<Cell> mazeQueue = new LinkedList<>();
        Cell start = new Cell(startR, startC, '@');
        Cell end = new Cell(endR, endC, '*');

        maze[startR][startC] = '@';
        mazeQueue.offer(start);
        while(!mazeQueue.isEmpty()){
            if(mazeQueue.peek().row == endR && mazeQueue.peek().col == endC){
                gui.setStatusText("Maze is solvable");
                solvable = true;
                break;
            }
            else{
                for(Cell c : checkForNeighbours(maze, mazeQueue.poll())){
                    mazeQueue.offer(c);
                    maze[c.row][c.col] = c.character;
                    gui.drawMaze(maze);
                    try { Thread.sleep(50);} catch (Exception e) {}
                }
            }
        }
        if(!solvable)
            gui.setStatusText("Maze is unsolvable");
    }
    
    public ArrayList<Cell> checkForNeighbours(char[][] maze, Cell cell){
        ArrayList<Cell> neighbours = new ArrayList<>();
        // check up
        if(cell.row > 0 && maze[cell.row - 1][cell.col] == ' ') neighbours.add(new Cell(cell.row - 1, cell.col, '@'));
        // check left
        if(cell.col > 0 && maze[cell.row][cell.col - 1] == ' ') neighbours.add(new Cell(cell.row, cell.col - 1, '@'));
        // check right
        if(cell.col < maze[0].length - 1 && maze[cell.row][cell.col + 1] == ' ') neighbours.add(new Cell(cell.row, cell.col + 1, '@'));
        // check down
        if(cell.row < maze.length - 1 && maze[cell.row + 1][cell.col] == ' ') neighbours.add(new Cell(cell.row + 1, cell.col, '@'));

        return neighbours;
    }
}
