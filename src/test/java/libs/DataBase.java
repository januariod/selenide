package libs;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.nio.file.Files.readAllBytes;

public class DataBase {

    private final String url = "jdbc:postgresql://pgdb/ninjaplus";
    private final String user = "postgres";
    private final String password = "qaninja";

    private Connection connection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void deleteMovie(String title) {
        String SQL = "delete from public.movies where title = ?;";

        try {
            PreparedStatement query = this.connection().prepareStatement(SQL);
            query.setString(1, title);
            query.executeQuery();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void restartTable() {
        String executionPath = System.getProperty("user.dir");
        String operationalSystem = System.getProperty("os.name");
        String target;
        String moviesSQL;

        if (operationalSystem.contains("Windows")) {
            target = executionPath + "\\src\\main\\resources\\sql\\movies.sql";
        } else {
            target = executionPath + "/src/main/resources/sql/movies.sql";
        }

        try {
            moviesSQL = new String(readAllBytes(Paths.get(target)));
            PreparedStatement query = this.connection().prepareStatement(moviesSQL);
            query.executeQuery();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
