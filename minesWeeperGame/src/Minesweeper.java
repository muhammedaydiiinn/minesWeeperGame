import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Minesweeper {

    static GameBoard gameBoard;
    int rowLength, colLength;

    int round = 0, mineCount = 0;
    public Minesweeper(int rowCount, int columnCount)
    {
        this.rowLength = rowCount;
        this.colLength = columnCount;
        gameBoard = new GameBoard(rowLength,colLength);

        prepareTheGameBoard();
    }

    private void prepareTheGameBoard(){

        int cellCount = rowLength * colLength;
        mineCount = cellCount / 4;

        List<Integer> randomMinedCells = new ArrayList<>();
        while (mineCount > 0)
        {
            int randomCellNo = (int) (Math.random() * cellCount);
            if (!randomMinedCells.contains(randomCellNo))
            {
                randomMinedCells.add(randomCellNo);
                mineCount--;
            }
        }

        for (int r = 0; r < rowLength; r++) {
            for (int c = 0; c < colLength; c++) {

                int no = (r * colLength) + c;
                boolean isMined = randomMinedCells.contains(no);

                Cell cell = new Cell(isMined, r, c, no);

                gameBoard.addCellToBoard(cell, r, c);
            }
        }

        for(Cell cell : gameBoard.getAllCellsList())
        {
            for (Cell neighbor : gameBoard.getAllCellsList())
            {
                if(cell.getCellNo() != neighbor.getCellNo())
                {
                    int cRow = cell.getRowNum();
                    int nRow = neighbor.getRowNum();
                    int cCol = cell.getColNum();
                    int nCol = neighbor.getColNum();
                    if((cRow == nRow - 1 || cRow == nRow || cRow == nRow + 1) && (cCol == nCol - 1 || cCol == nCol || cCol == nCol + 1))
                    {
                        if(neighbor.isMined()) cell.setClue(cell.getClue() + 1);
                    }
                }
            }
        }

        nextRound();
    }
    private void nextRound(){

        if(!areThereSelectableSafeCell())
        {
            gameOver(true);
            return;
        }

        round++;
        System.out.println("============ TUR " + round + " ============");
        printBoard();
        System.out.println("satır------------------------------");
        int enteredRow = getRowNumberFromUser();
        int enteredCol = getColNumberFromUser();

        checkEntry(enteredRow, enteredCol);
    }
    private void printBoard(){
        System.out.println(gameBoard.toString());
    }
    private int getRowNumberFromUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Geçersiz Giriş(min: 0 & max: " + (rowLength - 1) + ") : ");
        int input = scanner.nextInt();

        if(input < 0 || input >= rowLength){
            System.out.print("Geçersiz Giriş! Yeniden dene.");
            return getRowNumberFromUser();
        }
        else return input;
    }
    private int getColNumberFromUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Sütun sayısını giriniz(min: 0 & max: " + (colLength - 1) + ") : ");
        int input = scanner.nextInt();

        if(input < 0 || input >= colLength){
            System.out.print("Geçersiz Giriş! Yeniden dene.");
            return getColNumberFromUser();
        }
        else return input;
    }
    private void checkEntry(int row, int col){
        Cell cell = gameBoard.getCellFromBoard(row, col);

        if(cell.isMined())
        {
            gameOver(false);
        }
        else if(cell.isVisible())
        {
            System.out.println("Bu zaten seçildi!");
            nextRound();
        }
        else
        {
            cell.setVisible(true);
            openTheNeighborCells(row, col);
        }
    }
    private void openTheNeighborCells(int row, int col){
        for (int r = 0; r < rowLength; r++) {
            for (int c = 0; c < colLength; c++) {
                if(r == row && (c == col - 1 || c == col + 1))
                {
                    gameBoard.getCellFromBoard(r,c).setVisible(!gameBoard.getCellFromBoard(r,c).isMined());
                }

                if(c == col && (r == row -1 || r == row + 1))
                {
                    gameBoard.getCellFromBoard(r,c).setVisible(!gameBoard.getCellFromBoard(r,c).isMined());
                }
            }
        }

        nextRound();
    }
    private boolean areThereSelectableSafeCell(){
        for (int r = 0; r < rowLength; r++) {
            for (int c = 0; c < colLength; c++) {
                if(!gameBoard.getCellFromBoard(r,c).isVisible() && !gameBoard.getCellFromBoard(r,c).isMined())
                {
                    return true;
                }
            }
        }
        return false;
    }
    private void gameOver(boolean isWon){
        if(isWon)
        {
            System.out.println("\n\nOyun bitti...\n***** TEBRİKLER! Kazandın *****");
            for(Cell cell : gameBoard.getAllCellsList())
                cell.setVisible(true);
            System.out.println(gameBoard.toString());
        }
        else {
            System.out.println("\n\nOyun bitti...\n***** Kaybettin!!! *****");
            for(Cell cell : gameBoard.getAllCellsList())
                cell.setVisible(true);
            System.out.println(gameBoard.toString());
        }
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.print("Yeni bir oyun oynamak ister misin? Evet(e) veya Hayır(h) : ");
            String input = scanner.next();
            if(input.equalsIgnoreCase("e")){
                Main.startNewGame();
            }
            else if(input.equalsIgnoreCase("h")){

                System.out.println("OYUNDAN ÇIKILIYOR...\n");
                break;
            }
            else System.out.println("Geçersiz Girdi!\n");
        }
    }
}
