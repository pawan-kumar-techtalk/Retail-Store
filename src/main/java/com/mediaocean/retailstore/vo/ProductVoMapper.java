package com.mediaocean.retailstore.vo;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductVoMapper implements ResultSetMapper<ProductVo> {

    @Override
    public ProductVo map(int index, ResultSet rs, StatementContext ctx) throws SQLException {
        return new ProductVo(rs.getInt("ID"),
                rs.getString("NAME"),
                rs.getInt("CATEGORY_ID"),
                rs.getInt("PRICE"));

    }
}
