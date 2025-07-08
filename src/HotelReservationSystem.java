/**
 * Final Submission, TU-Java-Session1-HotelRoomReservation
 */

import java.util.Arrays;
import java.util.Scanner;

public class HotelReservationSystem {
    private boolean[] roomStatus = new boolean[10];
    private int[] roomNumbers = {101, 102, 103, 104, 105, 106, 107, 108, 109, 110};
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        HotelReservationSystem hotelReservationSystem = new HotelReservationSystem();
        hotelReservationSystem.run();
    }

    private static void initialMessage() {
        System.out.println("\nWelcome to the Hotel Reservation System:");
        System.out.println("1. View Available Rooms");
        System.out.println("2. Book a Room");
        System.out.println("3. Cancel Reservation");
        System.out.println("4. Exit");
    }

    private void run() {
        int choice = 0;

        initialMessage();

        while (choice != 4) {
            printMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAvailableRooms();
                    break;
                case 2:
                    bookRoom();
                    break;
                case 3:
                    cancelReservation();
                    break;
                case 4:
                    exit();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void printMenu() {
        System.out.print("Enter your choice: ");
    }

    private void viewAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (int i = 0; i < roomNumbers.length; i++) {
            if (!roomStatus[i]) {
                System.out.print(roomNumbers[i] + " ");
            }
        }
        System.out.println("\n");
    }

    private void bookRoom() {
        System.out.print("Enter the room number to book: ");
        int roomNumber = scanner.nextInt();

        int roomIndex = getRoomIndex(roomNumber);
        if (roomIndex == -1) {
            System.out.println("Room " + roomNumber + " is invalid.");
            System.out.println("\n");
            return;
        }

        if (roomStatus[roomIndex]) {
            System.out.println("Room " + roomNumber + " is already booked.");
            System.out.println("\n");
            return;
        }

        roomStatus[roomIndex] = true;
        System.out.println("Room " + roomNumber + " booked successfully.");
        System.out.println();
    }


    private void cancelReservation() {
        System.out.print("Enter the room number to cancel: ");
        int roomNumber = scanner.nextInt();

        boolean isInRoomNumbers = Arrays.stream(roomNumbers).anyMatch(number -> number == roomNumber);
        if (!isInRoomNumbers) {
            System.out.println("Room " + roomNumber + " is invalid.");
            System.out.println("\n");
            return;
        }

        int roomIndex = getRoomIndex(roomNumber);
        if (!roomStatus[roomIndex]) {
            System.out.println("Room " + roomNumber + " is not booked.");
            System.out.println("\n");
            return;
        }

        roomStatus[roomIndex] = false;
        System.out.println("Room " + roomNumber + " reservation cancelled.");
        System.out.println();
    }

    private void exit() {
        System.out.println("Thank you!");
    }

    private int getRoomIndex(int roomNumber) {
        for (int i = 0; i < roomNumbers.length; i++) {
            if (roomNumbers[i] == roomNumber) {
                return i;
            }
        }
        return -1;
    }
}