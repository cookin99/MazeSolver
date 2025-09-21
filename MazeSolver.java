import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class MazeSolver {

    /*
     TODO design the fields.
     */
char[][] board;
    /*
     TODO design the constructor.
     */
    MazeSolver(String fileName){
        try{
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            String[] dimensions = reader.nextLine().split(" ");
            this.board = new char[Integer.parseInt(dimensions[0])][Integer.parseInt(dimensions[1])];
            int row = 0;
            while (reader.hasNextLine()) {
                String curLine = reader.nextLine();
                for (int i = 0; i < curLine.length(); i++) {
                    char ch = curLine.charAt(i);
                    board[row][i] = ch;
                }
                row++;
            }
            reader.close();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Recursively solves the maze, returning a solution if it exists,
     * and null otherwise. We use a simple backtracking algorithm
     * in the helper.
     * @return a solution to the maze, or null if it does not exist.
     */
    char[][] solve() {
        char[][] solution = new char[board.length][board[0].length];
        for(int i =0; i< board.length;i++){
            for (int j = 0; j < board[0].length; j++){
                solution[i][j]=board[i][j];
            }
        }
        if (solution[0][0] == '#' || solution[solution.length - 1][solution[0].length - 1] == '#') {
            return null;
        }
        if (solveHelper(0,0,solution)) {
            return solution;
        }
        return null;
    }

    /**
     * Recursively solves the maze, returning true if we ever reach
     * the exit. We try al possible paths from the current cell, if
     * they are reachable. If a path ends up being a dead end, we
     * backtrack and try another path.
     * @param r the row of the current cell.
     * @param c the column of the current cell.
     * @param sol the current solution to the maze.
     * @return true if we are at the exit, false otherwise.
     */
    private boolean solveHelper(int r, int c, char[][] sol) {
        sol[r][c] = '*';
        if (r == sol.length - 1 && c == sol[0].length - 1) {
            return true;
        }
        int[][] dimensions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int i = 0; i < dimensions.length; i++) {
            int nextRow = r + dimensions[i][0];
            int nextCol = c + dimensions[i][1];
            if (nextRow >= 0 && nextRow < sol.length && nextCol >= 0 && nextCol < sol[0].length) {
                if (sol[nextRow][nextCol] == '.') {
                    if (solveHelper(nextRow,nextCol,sol)) {
                        return true;
                    }
                }
            }
        }

        sol[r][c] = '.';

        return false;
    }

    /**
     * a method that creates/edits a file and outputs the new maze given it being solved
     * with the placeholders that signify used step or unused
     * @param fileName the gile to output to
     * @param soln the solution
     */
    void output(String fileName, char[][] soln) {
        try {
            FileWriter writter = new FileWriter(fileName);

            if (soln != null) {
                for (int i = 0; i < soln.length; i++) {
                    for (int j = 0; j < soln[0].length; j++) {
                        writter.write(soln[i][j]);
                    }
                    if (i < soln.length - 1) {
                        writter.write("\n");
                    }
                }
            }
            writter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
