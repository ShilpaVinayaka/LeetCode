/*
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.
*/
class NumberOfIslands_200 {
    public static void Dfs(char[][] grid, int i, int j, int row, int col) {
        if(i<0 || j<0 || i>row-1 || j>col-1 || grid[i][j] != '1')
            return;

        if(grid[i][j] == '1') {
            grid[i][j] = 0;
            Dfs(grid, i+1, j, row, col); //down
            Dfs(grid, i-1, j, row, col); //top
            Dfs(grid, i, j+1, row, col); //right
            Dfs(grid, i, j-1, row, col); //left
        }
    }
    public int numIslands(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        int count = 0;
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[i].length; j++) {
                if(grid[i][j] == '1') {
                    count++;
                    Dfs(grid, i, j, row, col);
                }
            }
        }
        return count;
    }
}
