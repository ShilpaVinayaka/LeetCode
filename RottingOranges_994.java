/*
GRAPH - BFS
You are given an m x n grid where each cell can have one of three values:
0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
*/
import java.util.*;
class Solution {

    // Matrix coordinates
    static class Adj {
        int row = 0;
        int col = 0;

        public Adj(int x, int y) {
            this.row = x;
            this.col = y;
        }
    }

    //To check if the cell is valid and within Limits 
    public static boolean isValid(int rowI, int colI, int grid[][]) {
        int r = grid.length;
        int c = grid[0].length;
        return (rowI>=0 && rowI<r && colI>=0 && colI<c);
    }

    // To check if the cell is (-1,-1)
    public static boolean isDelim(Adj temp) {
        return (temp.row == -1 && temp.col == -1);
    }

    // To check if there are still fresh oranges remaining
    public static boolean checkForFreshO(int grid[][]) {
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[i].length; j++) {
                if(grid[i][j] == 1) 
                    return true;
            }
        }
        return false;
    }

    public int orangesRotting(int[][] grid) {
        Queue<Adj> q = new LinkedList<>();

        Adj temp;
        int ans = 0;

        // First Time frame: Store cells of all initial Rotten oranges
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[i].length; j++) {
                if(grid[i][j] == 2) 
                    // Adding coordinates to queue
                    q.add(new Adj(i, j));
            }
        }

        /*
         * Insert a delimiter coordinate (-1,-1) to separate new rottens
         * from initial rooten coordinates
         */
        q.add(new Adj(-1, -1));

        while(!q.isEmpty()){

            //Start of new time frame
            boolean flag = false;

            while(!isDelim(q.peek())) {
                temp = q.peek();

                //right
                //Checking if right adjacent cell exisits and if the ele is equal to 1
                if(isValid(temp.row, temp.col+1, grid) && grid[temp.row][temp.col+1] == 1){
                    //If it is the first orange to get rotten by navigating neighbouring cells
                    if(!flag) {
                        ans++;
                        flag = true;
                    }
                
                // Make the right orange rotten
                grid[temp.row][temp.col+1] = 2;

                q.add(new Adj(temp.row, ++temp.col));

                // Moving back to current cell
                --temp.col;
                }

                //bottom
                if(isValid(temp.row+1, temp.col, grid) && grid[temp.row+1][temp.col] == 1) {

                    if(!flag) {
                        ans++;
                        flag = true;
                    }

                grid[temp.row+1][temp.col] = 2;

                q.add(new Adj(++temp.row, temp.col));
                --temp.row;
                }
            
                //left
                if(isValid(temp.row, temp.col-1, grid) && grid[temp.row][temp.col-1] == 1) {

                    if(!flag) {
                        ans++;
                        flag = true;
                    }

                grid[temp.row][temp.col-1] = 2;

                q.add(new Adj(temp.row, --temp.col));
                ++temp.col;
                }

                //top
                if(isValid(temp.row-1, temp.col, grid) && grid[temp.row-1][temp.col] == 1) {

                    if(!flag) {
                        ans++;
                        flag = true;
                    }

                grid[temp.row-1][temp.col] = 2;

                q.add(new Adj(--temp.row, temp.col));
                ++temp.row;
                }
                q.remove();
            }
            //Pop Delimeter
            q.remove();

            if(!q.isEmpty()){
                q.add(new Adj(-1,-1));
            }
        }
        return (checkForFreshO(grid)) ? -1 : ans;
    }
}
