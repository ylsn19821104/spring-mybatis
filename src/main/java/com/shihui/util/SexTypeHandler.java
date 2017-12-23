package com.shihui.util;

import com.shihui.emodel.Sex;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes({Sex.class})
public class SexTypeHandler implements TypeHandler<Sex> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void setParameter(PreparedStatement ps, int i, Sex parameter, JdbcType jdbcType) throws SQLException {
        logger.info("使用SexTypeHandler");
        ps.setString(i, parameter.name());
    }

    @Override
    public Sex getResult(ResultSet rs, String columnName) throws SQLException {
        logger.info("使用SexTypeHandler,ResultSet列名获取字符串");
        return Sex.getSex(rs.getString(columnName));
    }

    @Override
    public Sex getResult(ResultSet rs, int columnIndex) throws SQLException {
        return Sex.getSex(rs.getString(columnIndex));
    }

    @Override
    public Sex getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return Sex.getSex(cs.getString(columnIndex));
    }
}
