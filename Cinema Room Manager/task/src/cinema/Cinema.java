package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of rows
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        // Read the number of seats in each row
        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = scanner.nextInt();

        // Initialize the seating arrangement
        char[][] seats = new char[rows][seatsPerRow];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                seats[i][j] = 'S';
            }
        }

        int purchasedTickets = 0;
        int currentIncome = 0;
        int totalIncome = calculateTotalIncome(rows , seatsPerRow) ;

        boolean exit = false;

        while (!exit) {
            // Print the menu
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Show the seats
                    printSeatingArrangement(rows, seatsPerRow, seats);
                    break;
                case 2:
                    // Buy a ticket
                    boolean validTicket = false;
                    while (!validTicket) {
                        System.out.println("Enter a row number:");
                        int rowNumber = scanner.nextInt();

                        System.out.println("Enter a seat number in that row:");
                        int seatNumber = scanner.nextInt();

                        if (rowNumber < 1 || rowNumber > rows || seatNumber < 1 || seatNumber > seatsPerRow) {
                            System.out.println("Wrong input!");
                        } else if (seats[rowNumber - 1][seatNumber - 1] == 'B') {
                            System.out.println("That ticket has already been purchased!");
                        } else {
                            int ticketPrice = calculateTicketPrice(rows, seatsPerRow, rowNumber);
                            seats[rowNumber - 1][seatNumber - 1] = 'B';
                            purchasedTickets++;
                            currentIncome += ticketPrice;
                            System.out.println("Ticket price: $" + ticketPrice);
                            validTicket = true;
                        }
                    }
                    break;

                case 3:
                    // Show statistics
                    printStatistics(purchasedTickets , rows * seatsPerRow , currentIncome , totalIncome);
                    break;

                case 0:
                    // Exit the program
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    // Function to print the seating arrangement
    public static void printSeatingArrangement(int rows, int seatsPerRow, char[][] seats) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= seatsPerRow; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < seatsPerRow; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Function to calculate the ticket price
    public static int calculateTicketPrice(int rows, int seatsPerRow, int rowNumber) {
        int totalSeats = rows * seatsPerRow;
        if (totalSeats <= 60) {
            return 10;
        } else {
            int frontHalfRows = rows / 2;
            if (rowNumber <= frontHalfRows) {
                return 10;
            } else {
                return 8;
            }
        }
    }
    // Function to calculate the total income
    public static int calculateTotalIncome(int rows , int seatsPerRow) {
        int totalSeats = rows * seatsPerRow;

        if (totalSeats <= 60) {
            return totalSeats * 10;
        }else {
            int frontHalfRows = rows / 2;
            int backHalfRows =  rows - frontHalfRows;
            return (frontHalfRows * seatsPerRow * 10) + (backHalfRows * seatsPerRow * 8);
        }
    }
    // Function to print statistics
    public static void printStatistics(int purchasedTickets , int totalSeats , int currentIncome , int totalIncome) {
        double percentage = (double) purchasedTickets / totalSeats * 100;
        System.out.printf("Number of purchased tickets: %d%n", purchasedTickets);
        System.out.printf("Percentage: %.2f%%%n", percentage);
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n", totalIncome);
    }
}

