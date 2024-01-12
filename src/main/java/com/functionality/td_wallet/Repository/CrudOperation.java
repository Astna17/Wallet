package com.functionality.td_wallet.Repository;

import java.sql.SQLException;

public interface CrudOperation<T> {
    void save (T toSave) throws SQLException;

    void update(T toUpdate) throws SQLException;

    void delete(T toDelete) throws SQLException;


    void findAll(T toFindAll) throws SQLException;
}