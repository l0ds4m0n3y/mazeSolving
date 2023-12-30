
public interface MazeSolver {

    /**
     * This method is called when the start button is
     * clicked in the MazeGUI.  This method should solve the maze.
     * This method may call MazeGUI.drawMaze(...) whenever the
     * GUI display should be updated (after each step of the solution).
     *
     * The maze is provided as the first parameter.  It is a 2D array containing
     * characters that represent the spaces in the maze.  The following
     * characters will be found in the array:
     *    '#' - This represents a wall.
     *    ' ' - This represents an open space (corridor)
     *
     * When calling MazeGUI.drawMaze(...) to update the display, the GUI
     * will recognize the '#' and ' ' characters as well as the following:
     *    '@' - Means the cell is a space that has been explored
     *    '%' - Means that the cell is part of the best path to the goal.
     *
     * @param maze the maze (see above).
     * @param startR the row of the start cell.
     * @param startC the column of the start cell.
     * @param endR the row of the end (goal) cell.
     * @param endC the column of the end (goal) cell.
     */
    void solve(char[][] maze, int startR, int startC, int endR, int endC);

    /**
     * This method is called when the start button is
     * clicked in the MazeGUI.  This method should solve the maze.
     * This method may call MazeGUI.drawMaze(...) whenever the
     * GUI display should be updated (after each step of the solution).
     *
     * The maze is provided as the first parameter.  It is a 2D array containing
     * characters that represent the spaces in the maze.  The following
     * characters will be found in the array:
     *    '#' - This represents a wall.
     *    ' ' - This represents an open space (corridor)
     *
     * When calling MazeGUI.drawMaze(...) to update the display, the GUI
     * will recognize the '#' and ' ' characters as well as the following:
     *    '@' - Means the cell is a space that has been explored
     *    '%' - Means that the cell is part of the best path to the goal.
     * @param maze
     * @param startR
     * @param startC
     * @param endR
     * @param endC
     */
    void solveStack(char[][] maze, int startR, int startC, int endR, int endC);

    /**     
     * Sometimes our computers need a break, so you have to be patient with 
     * this algorithm. This algorithm takes random paths and sometimes it just needs
     * to think for a second... or ten. If you push this algorithm too far, it might
     * just give up and not solve the maze for you, so please be nice to it by encouraging
     * it when it doesn't feel like finishing.
     * 
     * This method is called when the start button is
     * clicked in the MazeGUI.  This method sometimes solves the maze.
     * This method may call MazeGUI.drawMaze(...) whenever the
     * GUI display should be updated (after each step of the solution).
     *
     * The maze is provided as the first parameter.  It is a 2D array containing
     * characters that represent the spaces in the maze.  The following
     * characters will be found in the array:
     *    '#' - This represents a wall.
     *    ' ' - This represents an open space (corridor)
     *
     * When calling MazeGUI.drawMaze(...) to update the display, the GUI
     * will recognize the '#' and ' ' characters as well as the following:
     *    '@' - Means the cell is a space that has been explored
     *    '%' - Means that the cell is part of the best path to the goal.
     * @param maze
     * @param startR
     * @param startC
     * @param endR
     * @param endC
     */
    void solveEpicly(char[][] maze, int startR, int startC, int endR, int endC);

    /**
     * 
     */
    void solveAStar(char[][] maze, int startR, int startC, int endR, int endC);
}
