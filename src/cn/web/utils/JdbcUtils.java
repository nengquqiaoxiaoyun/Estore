package cn.web.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author: huakaimay
 * @since: 2020-11-24
 */
public class JdbcUtils {

    private static DataSource dataSource;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    static {
        try {
            InputStream resourceAsStream = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 获取连接，并且关闭自动提交
     * @return
     */
    public static Connection getConnection() {
        Connection connection = threadLocal.get();
        if (connection == null) {
            try {
                connection = dataSource.getConnection();
                threadLocal.set(connection);
                connection.setAutoCommit(false);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return connection;
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
        DbUtils.closeQuietly(con, ps, rs);
    }

    /**
     * 全局过滤自动提交
     * cn.web.filter.TranstionFilter
     */
    public static void commit() {
        Connection connection = threadLocal.get();
        try {
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            DbUtils.closeQuietly(connection);
        }
        threadLocal.remove();
    }

    /**
     * 全局过滤自动回滚
     * cn.web.filter.TranstionFilter
     */
    public static void rollback() {
        Connection connection = threadLocal.get();
        try {
            connection.rollback();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            DbUtils.closeQuietly(connection);
        }
        threadLocal.remove();
    }


}
