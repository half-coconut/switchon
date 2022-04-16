package com.coconut.ds20.entity;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/2/4 15:22
 * File: ListTypeHandler
 * Project: dS9
 */

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * List转JDBC的字符串
 */
// JDBC数据类型
@MappedJdbcTypes(JdbcType.VARCHAR)
// java数据类型
@MappedTypes({List.class})
public class ListTypeHandler implements TypeHandler<List<String>> {
    /**
     * 保存到数据库时，List<String>转成String
     *
     * @param ps
     * @param i
     * @param parameter
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setParameter(PreparedStatement ps, int i,
                             List<String> parameter, JdbcType jdbcType) throws SQLException {
        String params = listToString(parameter);
        ps.setString(i, params);
    }

    /**
     * 集合拼接字符串
     *
     * @param parameter
     * @return
     */
    private String listToString(List<String> parameter) {
        if (parameter == null || parameter.size() <= 0) {
            return null;
        }
        String res = "";
        for (int i = 0; i < parameter.size(); i++) {
            if (i == parameter.size() - 1) {
                res += parameter.get(i);
                return res;
            }
            res += parameter.get(i) + ",";
        }
        return null;
    }

    /**
     * 从数据库获取时，String(VARCHAR)转List<String>
     * @param resultSet
     * @param s
     * @return
     * @throws SQLException
     */
    @Override
    public List<String> getResult(ResultSet resultSet, String s) throws SQLException {
        return Arrays.asList(resultSet.getString(s).split(","));
    }

    @Override
    public List<String> getResult(ResultSet resultSet, int i) throws SQLException {
        return Arrays.asList(resultSet.getString(i).split(","));
    }

    @Override
    public List<String> getResult(CallableStatement callableStatement, int i) throws SQLException {
        return Arrays.asList(callableStatement.getString(i).split(","));
    }
}
