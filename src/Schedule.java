import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Schedule {
    private final Person person; // Abstracted
    private final Room room;
    private final LocalDateTime scheduleDate;
    private final LocalDateTime bookingDate;

    public Schedule(Person person, Room room, LocalDateTime scheduleDate) {
        this.person = person;
        this.room = room;
        this.scheduleDate = scheduleDate;
        this.bookingDate = LocalDateTime.now();
    }

    public Person getPerson() { return person; }
    public Room getRoom() { return room; }
    public LocalDateTime getScheduleDate() { return scheduleDate; }
    public LocalDateTime getBookingDate() { return bookingDate; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("""
                ==== SCHEDULING CONFIRMATION ====
                Name: %s
                Section: %s
                Building: %s Room: %s
                Scheduled Date: %s
                Booking Date: %s
                ===================================
                """,
                person.getName(),
                person.getSection(),
                room.getBuilding(),      // Building first
                room.getRoomNumber(),    // Room number after building
                scheduleDate.format(formatter),
                bookingDate.format(formatter));
    }
}
