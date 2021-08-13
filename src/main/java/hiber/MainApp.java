package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User ilya = new User("Ilya", "Shaporto", "ZiK201091@ya.ru");
      User kristina = new User("Kristina", "Duleba", "duleba@eml.ru");
      User alla = new User("Alla", "Zhigulina", "alla2@yandex.ru");
      User roma = new User("Roma", "Ivanov", "romaIvanov@mail.io");

      Car zhiguli = new Car("Zhiguli", 252);
      Car bmw = new Car("BMW", 795);
      Car mercedes = new Car("Mercedes", 547);
      Car mitsubishi = new Car("Mitsubishi", 21014);

      userService.add(ilya.setCar(zhiguli).setUser(ilya));
      userService.add(kristina.setCar(bmw).setUser(kristina));
      userService.add(alla.setCar(mercedes).setUser(alla));
      userService.add(roma.setCar(mitsubishi).setUser(roma));


      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }


      System.out.println(userService.getUserByCar("BMW", 795));


      try {
         User notFoundUser = userService.getUserByCar("TOYOTA", 300);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }

      context.close();
   }
}
