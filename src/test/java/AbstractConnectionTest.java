import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static by.kleschenko.dbconnection.AbstractConnection.closeConnection;
import static by.kleschenko.dbconnection.AbstractConnection.getConnection;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AbstractConnectionTest {
    @BeforeEach
    public void setConnection() {
        getConnection();
    }

    @AfterEach
    public void close() {
        try {
            closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetConnection() throws SQLException {
        try (Connection connection = getConnection()) {
            assertTrue(connection.isValid(1));
            assertFalse(connection.isClosed());
        }
    }
}
