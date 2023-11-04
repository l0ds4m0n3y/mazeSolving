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

        Cell start = new Cell(startR, startC);
        Cell end = new Cell(endR, endC);

        maze[startR][startC] = '@';
        mazeQueue.offer(start);
        
        while(!mazeQueue.isEmpty()){
            Cell currentCell = mazeQueue.poll();

            if(currentCell.equals(end)){
                paintBestPath(maze, currentCell);
                gui.setStatusText("Maze is solvable");
                return;
            }

            for(Cell neighbour : checkForNeighbours(maze, currentCell)){
                mazeQueue.offer(neighbour);
                maze[neighbour.getRow()][neighbour.getCol()] = '@';
                gui.drawMaze(maze);
                // try { Thread.sleep(1);} catch (Exception e) {}
            }
        }
        gui.setStatusText("Maze is unsolvable");
    }

    public void paintBestPath(char[][] maze, Cell start){
        Cell ptr = start;
        while(ptr != null){
            maze[ptr.getRow()][ptr.getCol()] = '%';
            ptr =  ptr.parent;
        }
        gui.drawMaze(maze);
    }
    
    public ArrayList<Cell> checkForNeighbours(char[][] maze, Cell cell){
        ArrayList<Cell> neighbours = new ArrayList<>();
        int row = cell.getRow();
        int col = cell.getCol();
        // check up
        if(row > 0 && maze[row - 1][col] == ' ') 
                neighbours.add(new Cell(row - 1, col, cell));
        // check left
        if(col > 0 && maze[row][col - 1] == ' ') 
                neighbours.add(new Cell(row, col - 1, cell));
        // check right
        if(col < maze[0].length - 1 && maze[row][col + 1] == ' ') 
                neighbours.add(new Cell(row, col + 1, cell));
        // check down
        if(row < maze.length - 1 && maze[row + 1][col] == ' ') 
                neighbours.add(new Cell(row + 1, col, cell));

        return neighbours;
    }
}
