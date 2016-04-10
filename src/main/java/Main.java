import students.Event;
import students.Impl.ExcelWorkImpl;
import students.Student;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * Created by aleksejpluhin on 06.04.16.
 */
public class Main {
    public static void main(String[] args) throws IOException, MessagingException, InterruptedException {
  /*      Файл для парса папкаПользователя/repots/pars.xlsx
          Не реализовн динамический выбор файла
          aceessToken записать в открывшийся файл
          pars.xlsx
          Пример
          Имя Фамилия Почта Телефон idVk
          Петя Петров test@mail.ru +телефон id11111111

          Каждое письмо именуется

   */


        Event event = new Event("Новое проверочноые событие");
        Controller controller = new Controller("path");
        controller.setEvent(event);
        controller.distribution();
        System.out.println(controller.toString());


   }
}
