import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private static final String URL =
            "jdbc:sqlite:C:/Users/U S E R/Documents/NetBeansProjects/mini project/database/hotel_reservation_db.db";

    public static Connection getConnection() throws SQLException {

        Connection conn =
                DriverManager.getConnection(URL);

        conn.setAutoCommit(true);

        try (Statement stmt = conn.createStatement()) {

            stmt.execute("PRAGMA foreign_keys = ON");

            System.out.println(
                    "Connected to SQLite database successfully."
            );

            System.out.println(
                    "Database URL: " + URL
            );

            System.out.println(
                    "\n=== TABLES IN DATABASE ==="
            );

            try (ResultSet rs = stmt.executeQuery(
                    "SELECT name FROM sqlite_master " +
                    "WHERE type='table' " +
                    "ORDER BY name"
            )) {

                boolean found = false;

                while (rs.next()) {

                    found = true;

                    System.out.println(
                            rs.getString("name")
                    );
                }

                if (!found) {
                    System.out.println(
                            "NO TABLES FOUND!"
                    );
                }
            }
        }

        return conn;
    }
}