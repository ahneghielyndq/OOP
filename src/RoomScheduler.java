import java.time.LocalDateTime;
import java.util.*;

public class RoomScheduler {
    //Abstraction
    private final Map<String, Room> rooms = new HashMap<>();
    private final List<Schedule> schedules = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public RoomScheduler() { initializeRooms(); }

    private void initializeRooms() {

        String building = "GZB";
        for (int floor = 1; floor <= 3; floor++) {
            for (int room = 1; room <= 5; room++) {
                String roomCode = building + floor + String.format("%02d", room);
                rooms.put(roomCode, new Room(roomCode, building, 50));
            }
        }
    }

    public void start() {
        System.out.println("=== ROOM SCHEDULING SYSTEM ===");

        while (true) {
            System.out.println("\n1. Schedule Room\n2. View Available Rooms\n3. View All Schedules\n4. Exit");
            int choice = getIntInput("Enter choice: ");

            switch (choice) {
                case 1 -> scheduleRoom();
                case 2 -> viewAvailableRooms();
                case 3 -> viewAllSchedules();
                case 4 -> {
                    System.out.println("Thank you for using Room Scheduler");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // 🔹 Schedule Room (user enters only room number)
    private void scheduleRoom() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Section: ");
        String section = scanner.nextLine();

        Person student = new Student(name, section);

        System.out.println("Available Rooms:");
        rooms.values().stream()
                .filter(Room::isAvailable)
                .sorted(Comparator.comparing(Room::getBuilding)
                        .thenComparing(Room::getRoomCode))
                .forEach(room ->
                        System.out.println("Building: " + room.getBuilding() +
                                " Room: " + room.getRoomNumber())
                );

        System.out.print("Enter Room Number to book (e.g., 101): ");
        String roomNumber = scanner.nextLine().trim();

        // Find the room by room number and availability
        Room room = rooms.values().stream()
                .filter(r -> r.getRoomNumber().equals(roomNumber) && r.isAvailable())
                .findFirst()
                .orElse(null);

        if (room != null) {
            room.book();  // mark as booked
            Schedule schedule = new Schedule(student, room, LocalDateTime.now());
            schedules.add(schedule);
            System.out.println(schedule);
        } else {
            System.out.println("Invalid room or already booked.");
        }
    }

    //View Available Rooms (sorted by building then room)
    private void viewAvailableRooms() {
        System.out.println("\n=== AVAILABLE ROOMS ===");
        rooms.values().stream()
                .filter(Room::isAvailable)
                .sorted(Comparator.comparing(Room::getBuilding)
                        .thenComparing(Room::getRoomCode))
                .forEach(room ->
                        System.out.println("Building: " + room.getBuilding() +
                                " Room: " + room.getRoomNumber())
                );
    }

    private void viewAllSchedules() {
        if (schedules.isEmpty()) System.out.println("No schedules yet.");
        else schedules.forEach(System.out::println);
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number!");
            }
        }
    }
}


