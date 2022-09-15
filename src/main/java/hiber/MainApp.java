package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      System.out.println("Добавили 5 user-ов с авто:");
      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Audi", 1)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Kia", 2)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("VAZ", 3)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Mazda", 4)));
      userService.add(new User("User5", "Lastname5", "user5@mail.ru", new Car("Kia", 2)));
      System.out.println("_________________________________");


      System.out.println("Прочитали всех user-ов из БД:");
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getUserCar());
         System.out.println();
      }
      System.out.println("_________________________________");


      String carModel = "Kia";
      int carSeries = 2;
      System.out.println("Ищем user-а с авто, у которого: Model = " + carModel + ", Series = " + carSeries);

      users = userService.getUsersByCarModelAndSeries(carModel, carSeries);

      if (users.size() == 0) {
         System.out.println("Не найдено user-ов с таким авто");
      } else {
         System.out.println("Нашли " + users.size() + " user-ов:");
         for (User user : users) {
            System.out.println("Id = "+user.getId());
            System.out.println("First Name = "+user.getFirstName());
            System.out.println("Last Name = "+user.getLastName());
            System.out.println("Email = "+user.getEmail());
            System.out.println("Car = " + user.getUserCar());
            System.out.println();
         }
      }

      context.close();
   }
}
