package students;

import java.util.List;

/**
 * Created by aleksejpluhin on 06.04.16.
 */
public class Event {
    String textEvent;


    public Event(String textEvent) {
        this.textEvent = textEvent;
    }

    public void setMessageText(List<Student> students) {
        for(Student student : students) {
            student.setMessage("Добрый день, " + student.getName() + " " + student.getSurname() + "!" + textEvent);
        }
    }

}
