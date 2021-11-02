import model.User;
import service.UserService;
import service.UserServiceImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.createUsersTable();

        userService.saveUser("Ivan","Ivanov",(byte)50);
        userService.saveUser("Andrey","Mironov",(byte)33);
        userService.saveUser("Denis","Lavrov",(byte)27);
        userService.saveUser("Sergey","Bodrov",(byte)30);

        List<User> users=userService.getAllUsers();
        System.out.println("Users after inserting:");
        users.forEach(e-> System.out.println(e.toString()));

        userService.cleanUsersTable();
        users=userService.getAllUsers();
        System.out.println("Users after cleaning:");
        users.forEach(e -> System.out.println(e.toString()));

        userService.dropUsersTable();

        /**
         *  Создание таблицы User(ов)
         *  Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в консоль
         *  ( User с именем – name добавлен в базу данных )
         *  Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
         *  Очистка таблицы User(ов)
         *  Удаление таблицы
         */
    }
}
