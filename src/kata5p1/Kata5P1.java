package kata5p1;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import static kata5p1.MailListReader.read;

public class Kata5P1 {

    public static void main(String[] args) throws FileNotFoundException, FileNotFoundException {
        String selectAllSQL = "SELECT * FROM PEOPLE";
        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(selectAllSQL)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t"
                        + rs.getString("Nombre") + "\t"
                        + rs.getString("Apellidos") + "\t"
                        + rs.getString("Departamento") + "\t");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String createSQL = "CREATE TABLE IF NOT EXISTS EMAIL (\n"
                + " Id integer PRIMARY KEY AUTOINCREMENT,\n"
                + " Mail text NOT NULL);";
        try (Connection conn = connect();
                Statement stmt = conn.createStatement()) {
            stmt.execute(createSQL);
            System.out.println("Tabla creada");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        List<String> mailList = read("email/email.txt");
        for (String email : mailList) {
            insert(email);
        }

    }

    public static Connection connect() {
        String url = "jdbc:sqlite:KATA5.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void insert(String email) {
        String insertSQL = "INSERT INTO EMAIL(Mail) VALUES(?)";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
