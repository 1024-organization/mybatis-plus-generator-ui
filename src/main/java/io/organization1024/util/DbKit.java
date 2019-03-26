package io.organization1024.util;

import io.organization1024.enums.RestEnum;
import io.organization1024.exception.GeneratorUiException;
import io.organization1024.vo.DataSourceVo;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author imyzt
 * @date 2019/1/23 10:22
 * @description 数据库工具包
 */
@Slf4j
public class DbKit {

    private DbKit() { }

    public static List<String> tableNames(DataSourceVo dataSourceConfig, String dbName) {
        LinkedList <String> tableNames = new LinkedList <>();
        try(Connection conn = getConn(dataSourceConfig)) {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet resultSet =
                    metaData.getTables(null, null, null, new String[]{"TABLE", "VIEW"});
            while (resultSet.next()) {
                String tableCat = resultSet.getString("TABLE_CAT");
                if (Objects.equals(tableCat.toUpperCase(), dbName.toUpperCase())) {
                    tableNames.add(resultSet.getString(3));
                }
            }
        } catch (SQLException e) {
            throw new GeneratorUiException(RestEnum.SQL_ERROR, e);
        } catch (ClassNotFoundException e) {
            throw new GeneratorUiException(RestEnum.DB_DRIVER_ERROR, e);
        }
        return tableNames;
    }

    private static Connection getConn(DataSourceVo dataSourceConfig) throws ClassNotFoundException, SQLException {
        Connection conn;
        Class.forName(dataSourceConfig.getDriverName());
        conn = DriverManager.getConnection(dataSourceConfig.getUrl(),
                dataSourceConfig.getUsername(),
                dataSourceConfig.getPassword());
        return conn;
    }

}
