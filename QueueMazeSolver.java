import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

public class QueueMazeSolver implements MazeSolver{
    private MazeGUI gui;
    
    QueueMazeSolver(){
        gui = new MazeGUI(this);
    }
    /**
     * Once the algorithm finds the exit, it paints a path back to the start to show
     * the user how to solve it
     * @param maze
     * @param start
     */
    public void paintBestPath(char[][] maze, Cell start){
        Cell ptr = start;
        while(ptr != null){
            maze[ptr.getRow()][ptr.getCol()] = '%';
            ptr =  ptr.parent;
        }
        gui.drawMaze(maze);
    }

    /**
     * Checks a cell's neighbours and adds them to a list
     * @param maze
     * @param cell
     * @return a list of all the cell's neighbours
     */
    public ArrayList<Cell> checkForNeighbours(char[][] maze, Cell cell){
        ArrayList<Cell> neighbours = new ArrayList<>();
        int row = cell.getRow();
        int col = cell.getCol();

        // check left
        if(col > 0 && maze[row][col - 1] == ' ') 
                neighbours.add(new Cell(row, col - 1, cell));
        // check up
        if(row > 0 && maze[row - 1][col] == ' ') 
                neighbours.add(new Cell(row - 1, col, cell));
        // check right
        if(col < maze[0].length - 1 && maze[row][col + 1] == ' ') 
        neighbours.add(new Cell(row, col + 1, cell));
        // check down
        if(row < maze.length - 1 && maze[row + 1][col] == ' ') 
        neighbours.add(new Cell(row + 1, col, cell));
        
        return neighbours;
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

    @Override
    public void solveStack(char[][] maze, int startR, int startC, int endR, int endC) {
        Stack<Cell> mazeQueue = new Stack<>();

        Cell start = new Cell(startR, startC);
        Cell end = new Cell(endR, endC);

        maze[startR][startC] = '@';
        mazeQueue.push(start);
        
        while(!mazeQueue.isEmpty()){
            Cell currentCell = mazeQueue.pop();

            if(currentCell.equals(end)){
                paintBestPath(maze, currentCell);
                gui.setStatusText("Maze is solvable");
                return;
            }

            for(Cell neighbour : checkForNeighbours(maze, currentCell)){
                mazeQueue.push(neighbour);
                maze[neighbour.getRow()][neighbour.getCol()] = '@';
                gui.drawMaze(maze);
                // try { Thread.sleep(50);} catch (Exception e) {}
            }
        }
        gui.setStatusText("Maze is unsolvable");
    }
    
    //this one is a joke and should not be used as an actual algorithm
    @Override
    public void solveEpicly(char[][] maze, int startR, int startC, int endR, int endC) {
        Queue<Cell> mazeQueue = new LinkedList<>();
        Random rand = new Random();
        
        Cell start = new Cell(startR, startC);
        Cell end = new Cell(endR, endC);
        
        maze[startR][startC] = '@';
        mazeQueue.offer(start);
        
        while(!mazeQueue.isEmpty()){
            /*
             * you never know if that path you ignored was the most optimal way to go,
             * so this algortihm chooses at random which path to search next!
             */
            LinkedList<Cell> temp = new LinkedList<>(mazeQueue);
            Collections.shuffle(temp);
            mazeQueue.clear();
            mazeQueue = temp;

            Cell currentCell = mazeQueue.poll();
            
            if(currentCell.equals(end)){
                paintBestPath(maze, currentCell);
                gui.setStatusText("Maze is kinda solvable"); // it did it's best
                return;
            }
            
            for(Cell neighbour : checkForNeighbours(maze, currentCell)){
                mazeQueue.offer(neighbour);
                maze[neighbour.getRow()][neighbour.getCol()] = '@';
                gui.drawMaze(maze);
            }

            /*
             * Sometimes when an algorithm works too hard, it might want to stop. So, please
             * be nice to this algorithm by typing please when it wants to stop and it might
             * continue solving the maze.
             */
            if(rand.nextInt(1000) == 69){
                gui.setStatusText("I give up, this might or might not be possiible");
                String userResponse = JOptionPane.showInputDialog(null,"type \"please\" to encourage it", "I GIVE UP", JOptionPane.INFORMATION_MESSAGE);
                if(!userResponse.toLowerCase().equals("please"))
                    return;
                gui.setStatusText("K FINE");
            /*
             * This is when the algorithm wants to think extra carefully about what it should
             * do next
             */
            }else if(rand.nextInt(1000) == 69){
                gui.setStatusText("hold on I am thinking...");
                try { Thread.sleep(rand.nextInt(7000) + 3000); } catch (Exception e) {}
            }
        }
        gui.setStatusText("Maze might be unsolvable"); // you never know!
    }
}
