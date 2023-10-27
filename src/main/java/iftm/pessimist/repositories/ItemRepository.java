package iftm.pessimisticlock.repositories;


import iftm.pessimisticlock.models.item;
import iftm.pessimisticlock.repositories.map.ItemMap;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public ItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public item findById(Long id) {
        String sql = "SELECT * FROM sock_item WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ItemMap());
    }

    public void save(item sockItem) {
        String sql = "INSERT INTO sock_item (id, name, quantity) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, sockItem.getId(), sockItem.getName(), sockItem.getQuant());
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM sock_item WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
