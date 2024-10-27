package cinema;


import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        int[][] cinemaSeatsMatrix = new int[rows][seats];
        commandDisplayManu(cinemaSeatsMatrix, scanner, rows, seats);
    }

    private static void commandDisplayManu(int[][] cinemaSeatsMatrix, Scanner scanner, int rows, int seats) {
        while (true) {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int command = scanner.nextInt();
            switch (command) {
                case 1 -> printCinemaSeats(cinemaSeatsMatrix);
                case 2 -> buyTicket(cinemaSeatsMatrix, rows, scanner, seats);
                case 3 -> showStatistics(cinemaSeatsMatrix, rows, seats);
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid command. Please try again.");
            }
        }
    }

    private static void showStatistics(int[][] cinemaSeatsMatrix, int rows, int seats) {
        int purchasedTickets = 0;
        int currentIncome = 0;
        int totalIncome = getTotalIncome(rows, seats);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                if (cinemaSeatsMatrix[i][j] == 1) {
                    currentIncome += getSeatsPrice(rows, i + 1);
                    purchasedTickets++;
                }

            }
        }
        double percentage = (double) purchasedTickets / (rows * seats) * 100;
        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    private static int getTotalIncome(int rows, int seats) {
        int frontRows = rows / 2;
        int backRows = rows - frontRows;
        int frontSeatsIncome = frontRows * seats * 10;
        int backSeatsIncome = backRows * seats * 8;
        return frontSeatsIncome + backSeatsIncome;
    }

    private static void buyTicket(int[][] cinemaSeatsMatrix, int rows, Scanner scanner, int seats) {
        System.out.println();
        while (true) {
            System.out.println("Enter a row number:");
            int rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatNumber = scanner.nextInt();

            if (rowNumber < 1 || rowNumber > rows || seatNumber < 1 || seatNumber > seats) {
                System.out.println("Wrong input!");
            } else if (cinemaSeatsMatrix[rowNumber - 1][seatNumber - 1] == 1) {
                System.out.println("That ticket has already been purchased!");
            } else {
                int cinemaPrice = getSeatsPrice(rows, rowNumber);
                System.out.println("Ticket price: $" + cinemaPrice);
                reserveSeat(cinemaSeatsMatrix, rowNumber, seatNumber);
                break;
            }
        }

    }


    private static int getSeatsPrice(int rows, int rowNumber) {
        //System.out.println();
        int frontSeatsPrice = 10;
        int backSeatsPrice = 8;
        if (rows * rows <= 60) {
            return frontSeatsPrice;
        } else {
            int frontRows = rows / 2;
            return rowNumber > frontRows ? backSeatsPrice : frontSeatsPrice;
        }
    }


    private static void printCinemaSeats(int[][] cinemaSeatsMatrix) {
        System.out.println();
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= cinemaSeatsMatrix[0].length; i++) {
            System.out.printf("%d ", i);
        }
        System.out.println();
        for (int row = 0; row < cinemaSeatsMatrix.length; row++) {
            System.out.printf("%d ", row + 1);
            for (int col = 0; col < cinemaSeatsMatrix[row].length; col++) {
                if (cinemaSeatsMatrix[row][col] == 1) {
                    System.out.print("B ");
                } else {
                    System.out.print("S ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void reserveSeat(int[][] cinemaSeatsMatrix, int rowNumber, int seatNumber) {
        cinemaSeatsMatrix[rowNumber - 1][seatNumber - 1] = 1;
    }

}