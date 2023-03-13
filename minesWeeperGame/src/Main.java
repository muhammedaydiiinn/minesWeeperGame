import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        startNewGame();
    }

    static void startNewGame(){
        Scanner scanner = new Scanner(System.in);

        printStartMessage();

        int row, col;
        while (true)
        {
            System.out.print("Satır sayısını giriniz (min: 3 & max: 9) : ");
            row = scanner.nextInt();

            System.out.print("Sütun sayısını giriniz (min: 3 & max: 9) : ");
            col = scanner.nextInt();

            if(row >= 3 && row <= 9 && col >= 3 && col <= 9) break;
            else System.out.println("Geçersiz Giriş! Değerler minimum 3 ve maksimum 9 olmalıdır.");
        }

        System.out.println("\nOyun Başlıyor\n");

        Minesweeper minesweeper = new Minesweeper(row, col);

    }

    static void printStartMessage(){
        System.out.println("""

        ******************************* Mayın Tarlası Oyunu *******************************
        --                Matristeki satır ve sütun sayısını gireceksiniz             --
        
        --               Satır ve sütun değerleri en az 3 en fazla 9 olmalıdır        --
        
        --                  Mayınları yerleştirip, mayınlara yakalanmadan 
        --                         alanı temizlemek için sizden                       --
        --                     sıra ve sütun numaralarını isteyeceğim.                --
        
        --                         Endişelenme, sana yardım edeceğim.                 --
        --                                    BAŞLAYALIM...                           --
        --------------------------------------------------------------------------------
        """);
    }
}