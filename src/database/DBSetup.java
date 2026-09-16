import java.sql.Connection;
import java.sql.Statement;

public class DBSetup {

    public static void createTables() {

        // =========================
        // USERS TABLE
        // =========================

        String usersTable = """
            CREATE TABLE IF NOT EXISTS users (
                user_id TEXT PRIMARY KEY,
                name TEXT NOT NULL,
                email TEXT NOT NULL,
                phone TEXT NOT NULL
            )
            """;

        // =========================
        // ROOMS TABLE
        // =========================

        String roomsTable = """
            CREATE TABLE IF NOT EXISTS rooms (
                room_id TEXT PRIMARY KEY,
                room_number TEXT NOT NULL,
                room_type TEXT NOT NULL,
                bed_type TEXT NOT NULL,
                base_price REAL NOT NULL,
                extra_charge REAL NOT NULL,
                max_occupancy INTEGER NOT NULL
            )
            """;

        // =========================
        // RESERVATIONS TABLE
        // =========================

        String reservationsTable = """
            CREATE TABLE IF NOT EXISTS reservations (
                reservation_id TEXT PRIMARY KEY,
                user_id TEXT NOT NULL,
                room_id TEXT NOT NULL,
                reservation_date TEXT NOT NULL,
                check_in_date TEXT NOT NULL,
                check_out_date TEXT NOT NULL,
                status TEXT NOT NULL,
                total_amount REAL NOT NULL,

                FOREIGN KEY (user_id)
                    REFERENCES users(user_id),

                FOREIGN KEY (room_id)
                    REFERENCES rooms(room_id)
            )
            """;

        // =========================
        // PAYMENTS TABLE
        // =========================

        String paymentsTable = """
            CREATE TABLE IF NOT EXISTS payments (
                payment_id TEXT PRIMARY KEY,
                reservation_id TEXT NOT NULL,
                payment_date TEXT NOT NULL,
                amount REAL NOT NULL,
                method TEXT NOT NULL,
                status TEXT NOT NULL,

                FOREIGN KEY (reservation_id)
                    REFERENCES reservations(reservation_id)
            )
            """;

        // =========================
        // CREATE TABLES
        // =========================

        try (
                Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement()
        ) {

            stmt.execute(usersTable);
            stmt.execute(roomsTable);
            stmt.execute(reservationsTable);
            stmt.execute(paymentsTable);

            System.out.println(
                    "Database tables created successfully!"
            );

        } catch (Exception e) {

            System.out.println(
                    "Error creating database tables:"
            );

            e.printStackTrace();
        }
    }

    // =========================
    // MAIN
    // =========================

    public static void main(String[] args) {

        createTables();
    }
}