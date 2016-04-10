import mail.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import students.Event;
import students.Impl.ParserImpl;
import students.Parser;
import students.Student;
import vk.Vk;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




/**
 * Created by aleksejpluhin on 06.04.16.
 */
public class Controller {
 //   private Logger logger = LoggerFactory.getLogger(Controller.class);
    private List<Student> students = new ArrayList<>();
    private Parser parser;
    private Event event;
    private Mail mail = new Mail();
    private Vk vk = new Vk();


    public Controller(String filePath) throws IOException, MessagingException, InterruptedException {
        parser = new ParserImpl(filePath);
        students = parser.getStudents();
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public  void distribution() throws IOException {
        event.setMessageText(students);
        vk.distributionMessage(students);
        mail.distributionMessage(students);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(Student student : students) {
            str.append(student.toString() + "\n");
        }
        return  str.toString();

    }


}
