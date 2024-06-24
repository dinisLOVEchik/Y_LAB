import service.impl.BookingServiceImpl;
import service.impl.MeetingRoomServiceImpl;
import service.impl.UserServiceImpl;
import service.impl.WorkspaceServiceImpl;

import java.time.LocalDateTime;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        UserServiceImpl userService = new UserServiceImpl();
        WorkspaceServiceImpl workspaceService = new WorkspaceServiceImpl();
        MeetingRoomServiceImpl meetingRoomService = new MeetingRoomServiceImpl();
        BookingServiceImpl bookingService = new BookingServiceImpl();
        while (true){
            System.out.println("Введите команду:");
            String command = scanner.nextLine();
            switch (command){
                case "Создать пользователя":
                    System.out.println("Введите логин:");
                    String login = scanner.next();
                    System.out.println("Введите пароль:");
                    String password = scanner.next();
                    System.out.println(userService.createUser(login, password));
                    break;
                case "Список пользователей":
                    System.out.println("Список пользователей: ");
                    userService.getUsers().forEach(user -> System.out.println(user.getLogin()));
                    break;
                case "Удалить пользователя":
                    System.out.println("Введите имя пользователя, которого желаете удалить:");
                    System.out.println(userService.deleteUser(scanner.next()));
                    break;
                case "Получить пользователя":
                    System.out.println("Введите имя пользователя:");
                    userService.getUser(scanner.next());
                case "Создать рабочее место":
                    System.out.println("Введите название рабочего пространства:");
                    String workspaceName = scanner.next();
                    System.out.println("Введите вместимость:");
                    int workspaceCapacity = scanner.nextInt();
                    workspaceService.addWorkspace(workspaceName, workspaceCapacity);
                    break;
                case "Создать конференц-зал":
                    System.out.println("Введите название конференц-зала:");
                    String roomName = scanner.next();
                    System.out.println("Введите вместимость:");
                    int roomCapacity = scanner.nextInt();
                    meetingRoomService.addMeetingRoom(roomName, roomCapacity);
                    break;
                case "Создать бронь":
                    System.out.println("Введите логин пользователя:");
                    String userLogin = scanner.next();
                    System.out.println("Введите название рабочего места:");
                    String userWorkspaceName = scanner.next();
                    System.out.println("Введите название конференц-зала:");
                    String userRoomName = scanner.next();
                    System.out.println("Введите дату и время начала:");
                    LocalDateTime startDate = LocalDateTime.parse(scanner.next());
                    System.out.println("Введите дату и время конца:");
                    LocalDateTime endDate = LocalDateTime.parse(scanner.next());
                    bookingService.createBooking(userService.getUser(userLogin), workspaceService.getWorkspace(userWorkspaceName), meetingRoomService.getMeetingRoom(userRoomName), startDate, endDate);
                    break;
                case "Посмотреть все брони":
                    bookingService.getAllBookings().forEach(booking -> {
                        System.out.println("Пользователь: " + booking.getUser().getLogin());
                        System.out.println("Рабочее пространство: " + booking.getWorkspace().getName());
                        System.out.println("Конференц-зал: " + booking.getRoom().getName());
                        System.out.println("Дата и время начала: " + booking.getStartDate());
                        System.out.println("Дата и время конца: " + booking.getEndTime());
                        System.out.println();
                    });
                    break;
                case "Выход":
                    System.exit(0);
                default:
                    System.out.println("Неверная команда!");
                    break;
            }
        }
    }
}
