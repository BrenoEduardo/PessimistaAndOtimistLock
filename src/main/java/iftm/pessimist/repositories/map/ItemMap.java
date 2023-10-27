package iftm.pessimisticlock.repositories.map;


import iftm.pessimisticlock.models.item;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMap implements RowMapper<item> {

    @Override
    public item mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
        final item sockItem = new item();
        sockItem.setId(resultSet.getLong("id"));
        sockItem.setName(resultSet.getString("name"));
        sockItem.setQuant(resultSet.getInt("quantity"));
        return sockItem;
    }
}