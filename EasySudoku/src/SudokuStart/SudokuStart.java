package SudokuStart;
import java.util.Scanner;

public class SudokuStart {
   static int[][] sudokuBoard = new int[9][9];
   static boolean[][] rows = new boolean[9][9];
   static boolean[][] cols = new boolean[9][9];
   static boolean[][] blocks = new boolean[9][9];
   static boolean[][] locked = new boolean[9][9];
   public SudokuStart() {
      int i = 0;
      int k = 0;
      while (i < 9) {
         k = 0;
         while (k < 9) {
            sudokuBoard[i][k] = 0;
            rows[i][k] = false;
            cols[i][k] = false;
            blocks[i][k] = false;
            k ++;
         }
         i ++;
      }
      
   }
   private static int blockindex(int i, int k) {
      return (3*(i/3) + (k/3));
   }

   private static boolean issolved() {
      int i = 0;
      int k = 0;
      while (i < 9) {
         k = 0;
         while (k < 9) {
            if (sudokuBoard[i][k] == 0) {
               return false;
            }
            k ++;
         }
         i ++;
      }
      i = 0;
      k = 0;
      while (i < 9) {
         k = 0;
         while (k < 9) {
            if (!rows[i][k]) {
               return false;
            }
            k ++;
         }
         i ++;
      }
      i = 0;
      k = 0;
      while (i < 9) {
         k = 0;
         while (k < 9) {
            if (!cols[i][k]) {
               return false;
            }
            k ++;
         }
         i ++;
      }
      i = 0;
      k = 0;
      while (i < 9) {
         k = 0;
         while (k < 9) {
            if (!blocks[i][k]) {
               return false;
            }
            k ++;
         }
         i ++;
      }
      return true;
   }
   private static int move(int i, int k) {
      int n = 0;
      while(locked[i][k] || sudokuBoard[i][k] == 9) {
         if (locked[i][k]) {
            if (k == 0) {
               k = 8;
               i = i - 1;
            }
            else {
               k = k - 1;
            }
         }
         else {
            if (k == 0) {
               rows[i][sudokuBoard[i][k] - 1] = false;
               cols[k][sudokuBoard[i][k] - 1] = false;
               blocks[blockindex(i,k)][sudokuBoard[i][k] - 1] = false;
               sudokuBoard[i][k] = 0;
               k = 8;
               i = i - 1;
            }
            else {
               rows[i][sudokuBoard[i][k] - 1] = false;
               cols[k][sudokuBoard[i][k] - 1] = false;
               blocks[blockindex(i,k)][sudokuBoard[i][k] - 1] = false;
               sudokuBoard[i][k] = 0;
               k = k - 1;
            }
         }
      }
      rows[i][sudokuBoard[i][k] - 1] = false;
      cols[k][sudokuBoard[i][k] - 1] = false;
      blocks[blockindex(i,k)][sudokuBoard[i][k] - 1] = false;
      n = sudokuBoard[i][k];
      sudokuBoard[i][k] = 0;
      return n + k*10 + i*100;
   }
   private static void solve() {
      int i = 0;
      int k = 0;
      int n = 0;
      int num = 0;
      while (!issolved()) {
         reset();
         while (i < 9) {
            k = 0;
            while (k < 9) {
               n = 0;
               while (n < 9) {
                  if (locked[i][k]) {
                     n = 9;
                  }
                  else if (rows[i][n]) {
                     if (n == 8) {
                        if (k == 0) {
                           k = 8;
                           i = i - 1;
                           num = move(i,k);
                           i = num / 100;
                           k = (num % 100) / 10;
                           n = num % 10;
                        }
                        else {
                           k = k - 1;
                           num = move(i,k);
                           i = num / 100;
                           k = (num % 100) / 10;
                           n = num % 10;
                        }
                     }
                     else {
                        n ++;
                     }
                  }
                  else if (cols[k][n]) {
                     if (n == 8) {
                        if (k == 0) {
                           k = 8;
                           i = i - 1;
                           num = move(i,k);
                           i = num / 100;
                           k = (num % 100) / 10;
                           n = num % 10;
                        }
                        else {
                           k = k - 1;
                           num = move(i,k);
                           i = num / 100;
                           k = (num % 100) / 10;
                           n = num % 10;
                        }
                     }
                     else {
                        n ++;
                     }
                  }
                  else if (blocks[blockindex(i,k)][n]) {
                     if (n == 8) {
                        if (k == 0) {
                           k = 8;
                           i = i - 1;
                           num = move(i,k);
                           i = num / 100;
                           k = (num % 100) / 10;
                           n = num % 10;
                        }
                        else {
                           k = k - 1;
                           num = move(i,k);
                           i = num / 100;
                           k = (num % 100) / 10;
                           n = num % 10;
                        }
                     }
                     else {
                        n ++;
                     }
                  }
                  else {
                     sudokuBoard[i][k] = n + 1;
                     rows[i][n] = true;
                     cols[k][n] = true;
                     blocks[blockindex(i,k)][n] = true;
                     n = 9;
                  }
               }
               k ++;
            }
            i ++;
         }
      }
   }
   private static void sudokuPrint() {
      int i = 0;
      int k = 0;
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println(issolved());
      while (i < 9) {
         k = 0;
         while (k < 9) {
            System.out.print(sudokuBoard[i][k]);
            System.out.print(" ");
            if(((k + 1) % 3 == 0) && (k != 8) ) {
               System.out.print("| ");
            }
            k ++;
         }
         System.out.println();
         if(((i + 1) % 3 == 0) && (i != 8) ) {
            System.out.println("---------------------");
         }
         i ++;
      }
   }
   private static void reset() {
      int i = 0;
      int k = 0;
      int num; 
      while (i < 9) {
         k = 0;
         while (k < 9) {
            if (!locked[i][k] && sudokuBoard[i][k] != 0) {
               num = sudokuBoard[i][k];
               rows[i][num - 1] = false;
               cols[k][num - 1] = false;
               blocks[blockindex(i,k)][num - 1] = false;
            }
            k ++;
         }
         i ++;
      }
   }
   private static void sudokuGen() {
      Scanner scanner = new Scanner(System.in);
      int i = 0;
      int k = 0;
      int input;
      System.out.println("Enter Sudoku: ");
      while (i < 9) {
         k = 8;
         input = Integer.parseInt(scanner.nextLine());
         while (k >= 0) {
            if(input % 10 == 0) {
               sudokuBoard[i][k] = 0;
            }
            else {
               sudokuBoard[i][k] = input % 10;
               locked[i][k] = true;
               rows[i][input % 10 - 1] = true;
               cols[k][input % 10 - 1] = true;
               blocks[blockindex(i,k)][input % 10 - 1] = true;
            }
            input = input / 10;
            k --;
         }
         i ++;
      }
   }
   public static void main(String[] args) {
      sudokuGen();
      sudokuPrint();
      solve();
      sudokuPrint();
   }
}
