import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentId;
    private String name;
    private List<String> registeredCourseCodes;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourseCodes = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<String> getRegisteredCourseCodes() {
        return registeredCourseCodes;
    }

    public boolean registerCourse(String courseCode) {
        if (!registeredCourseCodes.contains(courseCode)) {
            registeredCourseCodes.add(courseCode);
            return true;
        }
        return false;
    }

    public boolean dropCourse(String courseCode) {
        return registeredCourseCodes.remove(courseCode);
    }
}
