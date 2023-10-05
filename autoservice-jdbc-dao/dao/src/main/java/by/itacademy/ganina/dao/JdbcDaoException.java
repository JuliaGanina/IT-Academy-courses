package by.itacademy.ganina.dao;

public class JdbcDaoException extends DaoException {

    public JdbcDaoException(String message, Throwable cause) {
        super(message, cause);
    }


    public static class ClientJdbcDaoException extends JdbcDaoException{

        public ClientJdbcDaoException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class TransportJdbcDaoException extends JdbcDaoException{

        public TransportJdbcDaoException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
