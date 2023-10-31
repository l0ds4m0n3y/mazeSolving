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
        for(Cell c : checkForNeighbours(maze, startR, startC)){
            mazeQueue.offer(c);
        }
        while(!mazeQueue.isEmpty()){
            System.out.println(mazeQueue.poll().character);
        }
        
    }
    
    public ArrayList<Cell> checkForNeighbours(char[][] maze, int row, int col){
        ArrayList<Cell> neighbours = new ArrayList<>();
        if(row > 0 && maze[row - 1][col] == ' ') neighbours.add(new Cell(row - 1, col, '@'));
        if(col > 0 && maze[row][col - 1] == ' ') neighbours.add(new Cell(row, col - 1, '@'));
        if(col < maze[0].length && maze[row][col + 1] == ' ') neighbours.add(new Cell(row, col + 1, '@'));
        if(row < maze.length && maze[row + 1][col] == ' ') neighbours.add(new Cell(row + 1, col, '@'));

        return neighbours;
    }

    public boolean checkIfEnd(Cell cell){
        return cell.getChar() == '*';
    }
}
