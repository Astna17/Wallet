package com.functionality.td_wallet.Repository;

import java.sql.SQLException;

public interface CrudOperation<T> {
    void insert(T toInsert) throws SQLException;

    void update(T toUpdate) throws SQLException;

    void delete(T toDelete) throws SQLException;

    void saveAll(T toSaveAll) throws SQLException;

    void findAll(T toFindAll) throws SQLException;
}