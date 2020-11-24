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
    private QueryRunner queryRunnerWithDataSource = new QueryRunner(JdbcUtils.getDataSource());


    public QueryRunner getQueryRunner() {
        return queryRunner;
    }

    public QueryRunner getQueryRunnerWithDataSource() {
        return queryRunnerWithDataSource;
    }

    {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        cls = (Class<T>) actualTypeArguments[0];
    }

    /**
     * 增删改统一操作
     */
    public void update(Connection con, String sql, Object... args) {
        try {
            queryRunner.update(con, sql, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    /**
     * 查询单个对象
     */
    public T getBean(Connection con, String sql, Object... args) {

        try {

            BeanHandler<T> userBeanHandler = new BeanHandler<T>(cls);
            return queryRunner.query(con, sql, userBeanHandler, args);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * 查询多个对象
     * 不需要传connectin 自己关闭
     *
     * @return List<T>
     */
    public List<T> getBeanList(String sql, Object... args) {

        try {
            BeanListHandler<T> listBeanHandler = new BeanListHandler(cls);
            return queryRunnerWithDataSource.query(sql, listBeanHandler, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


}
