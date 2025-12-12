public class Room implements Bookable {
    //Encapsulation
    private final String roomCode;
    private final String building;
    private final int capacity;
    private boolean isAvailable;

    public Room(String roomCode, String building, int capacity) {
        this.roomCode = roomCode;
        this.building = building;
        this.capacity = capacity;
        this.isAvailable = true;
    }
    //Encapsulation
    public String getRoomCode() { return roomCode; }
    public String getBuilding() { return building; }
    public int getCapacity() { return capacity; }
    public boolean isAvailable() { return isAvailable; }


    public String getRoomNumber() {
        if (roomCode.startsWith(building)) {
            return roomCode.substring(building.length());
        }
        return roomCode;
    }
    //Polymorphism
    @Override
    public void book() { isAvailable = false; }
    @Override
    public void release() { isAvailable = true; }

    @Override
    public String toString() {
        String displayCode = roomCode;
        if (roomCode.startsWith(building)) {
            displayCode = roomCode.substring(building.length());
        }

        return String.format("Room: %s | Building: %s | Available: %s",
                displayCode, building, isAvailable ? "Yes" : "No");
    }
}


