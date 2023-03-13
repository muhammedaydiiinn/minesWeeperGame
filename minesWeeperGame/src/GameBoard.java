import java.util.ArrayList;
import java.util.List;

public class GameBoard {

    int row, col;
    private Cell[][] boardGrid;

    private List<Cell> allCellsList = new ArrayList<>();
    public GameBoard(int row, int col) {
        this.row = row;
        this.col = col;

        this.boardGrid = new Cell[row][col];
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder("col->");
        for (int i = 0; i < col; i++) {
            result.append(" ").append(i);
        }

        result.append("\n---------------------------------");

        for (int r = 0; r < row; r++) {
            result.append("\n").append(r).append(" -> ");
            for (int c = 0; c < col; c++) {
                result.append(" ").append(boardGrid[r][c].toString());
            }

            result.append("   |");
        }

        return result.toString();
    }

    public void addCellToBoard(Cell cell, int rowIndex, int colIndex){
        this.boardGrid[rowIndex][colIndex] = cell;
        this.allCellsList.add(cell);
    }

    public Cell getCellFromBoard(int rowIndex, int colIndex){
        return boardGrid[rowIndex][colIndex];
    }

    public List<Cell> getAllCellsList() {
        return allCellsList;
    }
}