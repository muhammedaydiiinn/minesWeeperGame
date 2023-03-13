public class Cell {

    private final boolean isMined;
    private boolean isVisible = false;
    private final int rowNum;
    private final int colNum;
    private final int cellNumber;
    int clue = 0;

    public Cell(boolean isMined, int rowNum, int colNum, int cellNumber) {
        this.isMined = isMined;
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.cellNumber = cellNumber;
    }

    @Override
    public String toString() {
        if(isVisible())
        {
            if(isSafe()) return " ";
            else if(isMined()) return "x";
            else return "" + clue;
        }
        else return "-";
    }

    public boolean isMined() {
        return isMined;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isSafe() {
        return (getClue() == 0 && !isMined());
    }

    public int getRowNum() {
        return rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public int getCellNo() {
        return cellNumber;
    }

    public int getClue() {
        return clue;
    }

    public void setClue(int clue) {
        this.clue = clue;
    }

}
