package utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.imageio.IIOException;
import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryUtils {
    private static SqlSessionFactory sqlSessionFactory = null;
    private final static Class<SqlSessionFactoryUtils> LOCK = SqlSessionFactoryUtils.class;
    private SqlSessionFactoryUtils() {
    }

    public static SqlSessionFactory getSqlSessionFactory(){
        synchronized (LOCK){
            if (sqlSessionFactory != null){
                return sqlSessionFactory;
            }
            String resourse = "mybatis-config.xml";
            InputStream inputStream;
            try{
                inputStream = Resources.getResourceAsStream(resourse);
                sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
            }
            catch (IOException e){
                e.printStackTrace();
                return null;
            }
        }
        return sqlSessionFactory;
    }
    public static SqlSession openSession(){
        if (sqlSessionFactory == null){
            getSqlSessionFactory();
        }
        return sqlSessionFactory.openSession();
    }
}
