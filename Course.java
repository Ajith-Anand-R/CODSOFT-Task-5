import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private List<String> registeredStudentIds;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudentIds = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getAvailableSlots() {
        return capacity - registeredStudentIds.size();
    }

    public boolean registerStudent(String studentId) {
        if (registeredStudentIds.size() < capacity && !registeredStudentIds.contains(studentId)) {
            registeredStudentIds.add(studentId);
            return true;
        }
        return false;
    }

    public boolean removeStudent(String studentId) {
        return registeredStudentIds.remove(studentId);
    }

    public List<String> getRegisteredStudentIds() {
        return registeredStudentIds;
    }
}
