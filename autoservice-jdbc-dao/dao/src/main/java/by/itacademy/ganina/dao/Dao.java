package by.itacademy.ganina.dao;

public interface Dao <T>{

    Integer create(T model) throws DaoException;

    T read(Integer id) throws DaoException;

    Integer update (T model) throws DaoException, NoSuchFieldException, IllegalAccessException;

    Integer delete(Integer id) throws DaoException;
}
