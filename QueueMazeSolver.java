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
            if(mazeQueue.peek().equals(end)){
                paintBestPath(maze, mazeQueue.peek());
                gui.setStatusText("Maze is solvable");
                return;
            }
            else{
                for(Cell c : checkForNeighbours(maze, mazeQueue.poll())){
                    mazeQueue.offer(c);
                    maze[c.getRow()][c.getCol()] = '@';
                    gui.drawMaze(maze);
                    try { Thread.sleep(1);} catch (Exception e) {}
                }
            }
        }
            gui.setStatusText("Maze is unsolvable");
    }

    public void paintBestPath(char[][] maze, Cell start){
        Cell ptr = start;
        while(ptr != null){
            maze[ptr.getRow()][ptr.getCol()] = '%';
            gui.drawMaze(maze);
            ptr =  ptr.parent;
        }
    }
    
    public ArrayList<Cell> checkForNeighbours(char[][] maze, Cell cell){
        ArrayList<Cell> neighbours = new ArrayList<>();
        // check up
        if(cell.getRow() > 0 && maze[cell.getRow() - 1][cell.getCol()] == ' ') 
                neighbours.add(new Cell(cell.getRow() - 1, cell.getCol(), cell));
        // check left
        if(cell.getCol() > 0 && maze[cell.getRow()][cell.getCol() - 1] == ' ') 
                neighbours.add(new Cell(cell.getRow(), cell.getCol() - 1, cell));
        // check right
        if(cell.getCol() < maze[0].length - 1 && maze[cell.getRow()][cell.getCol() + 1] == ' ') 
                neighbours.add(new Cell(cell.getRow(), cell.getCol() + 1, cell));
        // check down
        if(cell.getRow() < maze.length - 1 && maze[cell.getRow() + 1][cell.getCol()] == ' ') 
                neighbours.add(new Cell(cell.getRow() + 1, cell.getCol(), cell));

        return neighbours;
    }
}
