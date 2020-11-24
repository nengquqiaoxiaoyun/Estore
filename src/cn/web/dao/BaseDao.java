package cn.web.dao;

import cn.web.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-24
 */
public abstract class BaseDao<T> {

    private Class<T> cls;
    private QueryRunner queryRunner = new QueryRunner();


    public QueryRunner getQueryRunner() {
        return queryRunner;
    }


    {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        cls = (Class<T>) actualTypeArguments[0];
    }

    /**
     * 增删改统一操作
     */
    public void update(String sql, Object... args) {
        Connection con = JdbcUtils.getConnection();
        try {
            queryRunner.update(con, sql, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }

    }


    /**
     * 查询单个对象
     */
    public T getBean(String sql, Object... args) {
        Connection con = JdbcUtils.getConnection();
        try {

            BeanHandler<T> userBeanHandler = new BeanHandler<T>(cls);
            return queryRunner.query(con, sql, userBeanHandler, args);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }

    /**
     * 查询多个对象
     *
     * @return List<T>
     */
    public List<T> getBeanList(String sql, Object... args) {
        Connection con = JdbcUtils.getConnection();

        try {
            BeanListHandler<T> listBeanHandler = new BeanListHandler(cls);
            return queryRunner.query(con, sql, listBeanHandler, args);
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }


}