package dannelysbeth.jetbrains_academy.cinema;
import java.util.Scanner;

public class CinemaApplication {

    public static void main(String[] args) {
        int currentPrice = 0, purchasedTickets = 0, totalIncome = 0;
        double currentPercentage = 0;
        Scanner scanner = new Scanner(System.in);
        int rows, columns, selRow, selCol, option;
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        columns = scanner.nextInt();
        char[] seats = new char[rows*columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                seats[i * columns + j] = 'S';
            }
        }
        menu(rows, columns, seats, currentPrice, purchasedTickets, totalIncome, currentPercentage);

        // Write your code here
    }
    public static int countPrice(int rows, int columns){
        int total = rows * columns;
        int price;
        if(total<=60){
            price = 10*rows*columns;
            return price;
        }
        price = rows / 2 * columns * 10 + (rows / 2 + rows % 2) * columns * 8;
        return price;
    }
    public static int getPriceOfSeat(int rows, int columns, int selRow){
        int total = rows * columns;
        int price;
        if(total <= 60){
            return 10;
        }
        if (rows / 2 < selRow) {
            return 8;
        }
        return 10;
    }
    public static void showSeats(int rows, int columns, char[] seatsArray){
        System.out.println("\nCinema:");
        String msg = "\s";
        for(int i=1; i<=columns; i++){
            msg = msg + i + " ";
        }
        System.out.printf(" %s%n",msg);
        for(int i=0; i<rows; i++){
            System.out.print(i+1);
            for(int j=0; j<columns; j++){
                System.out.print(" "+seatsArray[i*columns+j]);
            }
            System.out.println();
        }
    }
    public static int buyTicket(int rows, int columns, char[] seatsArray) {
        Scanner scanner = new Scanner(System.in);
        int selRow, selCol;
        System.out.println();
        System.out.println("Enter a row number:");
        selRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        selCol = scanner.nextInt();
        if (selRow > rows || selRow < 1 || selCol > columns || selCol < 1) {
            System.out.println("\nWrong input!");
            return buyTicket(rows, columns, seatsArray);
        } else if (seatsArray[(selRow - 1) * columns + selCol - 1] == 'B') {
            System.out.println("\nThat ticket has already been purchased!");
            return buyTicket(rows, columns, seatsArray);
        } else {
            int price = getPriceOfSeat(rows, columns, selRow);
            System.out.print("\nTicket price:");
            System.out.println("$"+price);
            seatsArray[(selRow-1)*columns+(selCol-1)] = 'B';
            return price;
        }


    }
    public static void menu(int rows, int columns, char[] seats, int currentPrice, int purchasedTickets, int totalIncome, double currentPercentage) {
        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        int price = 0;
        Scanner scanner = new Scanner(System.in);

        int option = scanner.nextInt();

        switch (option) {
            case 1 :
                showSeats(rows, columns, seats);
                menu(rows, columns, seats, currentPrice, purchasedTickets, totalIncome, currentPercentage);
                break;
            case 2 :
                currentPrice = buyTicket(rows, columns, seats);
                purchasedTickets++;
                totalIncome += currentPrice;
                currentPercentage = purchasedTickets / (rows * columns);
                menu(rows, columns, seats, currentPrice, purchasedTickets, totalIncome, currentPercentage);
                break;
            case 3 :
                price = countPrice(rows, columns);
                statistics(price, purchasedTickets, totalIncome, seats);
                menu(rows, columns, seats, currentPrice, purchasedTickets, totalIncome, currentPercentage);
                break;
            default :
                break;

        }
    }
    public static void statistics(int currentPrice, int purchasedTickets, int totalIncome, char [] seats) {
        System.out.printf("\nNumber of purchased tickets: %d", purchasedTickets);
        double currentPercentage = (double) purchasedTickets * 100 / (double) seats.length;
        System.out.printf("\nPercentage: %.2f", currentPercentage);
        System.out.printf("%s", "%");
        System.out.printf("\nCurrent income: $%d", totalIncome);
        System.out.printf("\nTotal income: $%d", currentPrice);
    }
}