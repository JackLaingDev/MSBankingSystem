import controllers.Controller;
import services.DatabaseService;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        DatabaseService db = new DatabaseService();
        controller.run();
    }
}