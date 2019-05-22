package com.itheima.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
 
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/03/21 12:20
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO: 编写工具类测试是否获取到连接
 */
public class MyBatisUtil {

    private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
    private static SqlSessionFactory sqlSessionFactory;

    /**
     *  TODO: 加载位于resources中的mybatis-config.xml配置文件
     *
     * */

    static{

        try{

            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        }catch(IOException e){

            e.printStackTrace();
            throw new RuntimeException(e);

        }
    }

    /**
     * 禁止外界通过new方式创建
     * */

    private MyBatisUtil(){}

    /**
     * 获取SqlSession
     * */

    public static SqlSession getSqlSession(){

        // 从当前线程中获取SqlSession对象
        SqlSession sqlSession = threadLocal.get();
        // 如果SqlSession对象为空
        if (sqlSession == null){
            // 在SqlSessionFactory非空的情况下，获取SqlSession对象
            sqlSession = sqlSessionFactory.openSession();
            // 将SqlSession对象与当前线程绑定在一起
            threadLocal.set(sqlSession);
        }

        // 返回SqlSession对象
        return sqlSession;
    }

    /**
     * 关闭SqlSession与当前线程分开
     * */
    public static void closeSqlSession(){
        // 从当前线程中获取SqlSession对象
        SqlSession sqlSession = threadLocal.get();

        // 如果SqlSession对象非空
        if(sqlSession != null){
            // 关闭SqlSession对象
            sqlSession.close();
            // 分开当前线程与SqlSession对象的关系，目的让GC尽早回收
            threadLocal.remove();
        }

    }

    /**
     *  TESTING
     * */

    public static void main(String [] args){
        Connection connection = MyBatisUtil.getSqlSession().getConnection();
        System.out.println(connection != null?"ConnectionSuccess":"ConnectionFailed");
    }
}
