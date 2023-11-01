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
        Queue<Cell> mazeQueue = new LinkedList<>();
        Cell start = new Cell(startR, startC, '@');
        mazeQueue.offer(start);
        while(!mazeQueue.isEmpty()){
            if(checkIfEnd(mazeQueue.peek())){
                gui.setStatusText("Maze is solvable");
                break;
            }
            else{
                for(Cell c : checkForNeighbours(maze, mazeQueue.poll())){
                    mazeQueue.offer(c);
                    gui.drawMaze(maze);
                }
            }
        }
        gui.setStatusText("Maze is unsolvable");
    }
    
    public ArrayList<Cell> checkForNeighbours(char[][] maze, Cell cell){
        ArrayList<Cell> neighbours = new ArrayList<>();
        if(cell.row > 0 && maze[cell.row - 1][cell.col] == ' ') neighbours.add(new Cell(cell.row - 1, cell.col, '@'));
        if(cell.col > 0 && maze[cell.row][cell.col - 1] == ' ') neighbours.add(new Cell(cell.row, cell.col - 1, '@'));
        if(cell.col < maze[0].length && maze[cell.row][cell.col + 1] == ' ') neighbours.add(new Cell(cell.row, cell.col + 1, '@'));
        if(cell.row < maze.length && maze[cell.row + 1][cell.col] == ' ') neighbours.add(new Cell(cell.row + 1, cell.col, '@'));

        return neighbours;
    }

    public boolean checkIfEnd(Cell cell){
        return cell.getChar() == '*';
    }
}
