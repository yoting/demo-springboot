package com.gusi.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gusi.demo.pojo.User;

@Repository
public class UserDaoImpl extends CommonDaoImpl implements UserDao<User>
{

    @Override
    public User getObjectById(long id)
    {
        String sql = "select * from demo_user where id=?";
        User user = jdbcTemplate.queryForObject(sql, new Object[] { id },
                new RowMapper<User>()
                {

                    @Override
                    public User mapRow(ResultSet rs, int arg1)
                            throws SQLException
                    {
                        User user = new User();
                        user.setId(rs.getLong("id"));
                        user.setName(rs.getString("uname"));
                        user.setCode(rs.getString("ucode"));
                        user.setType(rs.getInt("utype"));
                        return user;
                    }

                });
        return user;
    }

    @Override
    public boolean insertObject(User obj)
    {
        return false;
    }

}
