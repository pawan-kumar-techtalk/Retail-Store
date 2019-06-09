package com.mediaocean.retailstore.vo;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryVoMapper implements ResultSetMapper<CategoryVo> {

    @Override
    public CategoryVo map(int index, ResultSet rs, StatementContext ctx) throws SQLException {
        return new CategoryVo(rs.getInt("ID"), rs.getString("NAME"), rs.getInt("SALES_TAX"));

    }
}
