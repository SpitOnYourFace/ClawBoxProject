import java.sql.*;

public class MyDBConnection {
    static Connection conn = null;
    private static boolean initialized = false;

    static Connection getConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                return conn;
            }
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./DB/clawbox", "sa", "");
            if (!initialized) {
                try {
                    initDB();
                    initialized = true;
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Database initialization failed", e);
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("H2 driver not found", e);
        } catch (SQLException e) {
            throw new RuntimeException("Database connection failed", e);
        }
        return conn;
    }

    static void initDB() throws SQLException {
        try (Statement st = conn.createStatement()) {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS KATEGORII ("
                + "ID IDENTITY PRIMARY KEY, "
                + "NAME VARCHAR(100))");

            st.executeUpdate("CREATE TABLE IF NOT EXISTS DOSTAVCHICI ("
                + "ID IDENTITY PRIMARY KEY, "
                + "NAME VARCHAR(100), "
                + "ADRES VARCHAR(200))");

            st.executeUpdate("CREATE TABLE IF NOT EXISTS KOMPONENTI ("
                + "ID IDENTITY PRIMARY KEY, "
                + "NAME VARCHAR(200), "
                + "IDKATEGORIYA INT REFERENCES KATEGORII(ID), "
                + "IDDOSTAVCHIK INT REFERENCES DOSTAVCHICI(ID), "
                + "OPIS VARCHAR(300), "
                + "KOL INT, "
                + "CENA DECIMAL(8,2))");

            st.executeUpdate("CREATE TABLE IF NOT EXISTS KLIENTI ("
                + "ID IDENTITY PRIMARY KEY, "
                + "FNAME VARCHAR(100), "
                + "LNAME VARCHAR(100), "
                + "TELEFON VARCHAR(20))");

            st.executeUpdate("CREATE TABLE IF NOT EXISTS PORUCHKI ("
                + "ID IDENTITY PRIMARY KEY, "
                + "IDKOMPONENT INT REFERENCES KOMPONENTI(ID), "
                + "IDKLIENT INT REFERENCES KLIENTI(ID), "
                + "KOL INT, "
                + "DATAPR DATE, "
                + "DATAIZ DATE)");

            // Insert initial data only if tables are empty
            try (ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM KATEGORII")) {
                rs.next();
                if (rs.getInt(1) == 0) {
                    st.executeUpdate("INSERT INTO KATEGORII(NAME) VALUES('Nvidia Jetson Nano')");
                    st.executeUpdate("INSERT INTO KATEGORII(NAME) VALUES('512GB Hard Disc')");
                    st.executeUpdate("INSERT INTO KATEGORII(NAME) VALUES('Cases for the box')");
                }
            }

            try (ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM DOSTAVCHICI")) {
                rs.next();
                if (rs.getInt(1) == 0) {
                    st.executeUpdate("INSERT INTO DOSTAVCHICI(NAME, ADRES) VALUES('AliExpress', 'China')");
                    st.executeUpdate("INSERT INTO DOSTAVCHICI(NAME, ADRES) VALUES('Mouser', 'USA')");
                    st.executeUpdate("INSERT INTO DOSTAVCHICI(NAME, ADRES) VALUES('Farnell', 'UK')");
                }
            }
        }
    }
}
