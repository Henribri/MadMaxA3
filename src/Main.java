import model.Model;
import view.View;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        new View();
        Model model = new Model();
//        model.getAll();
    }
}
