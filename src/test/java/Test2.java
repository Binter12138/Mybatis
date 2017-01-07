import entity.User;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by Administrator on 2017/1/7.
 */
public class Test2 {

    @Test
    public void findByIdTest()
        {


            String resource = "mybatis_config.xml";

            Reader reader = null;
            SqlSession sqlSession = null;
            try {
                reader = Resources.getResourceAsReader(resource);
                SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
                sqlSession = sqlSessionFactory.openSession();
                UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
                User user = userMapper.getUser(1);
                System.out.println(user);

            } catch (IOException e) {
                e.printStackTrace();
            }finally {

                if (sqlSession != null) {
                    sqlSession.close();
                }
        }

    }



    @Test
    public void findByNameTest()
    {


        String resource = "mybatis_config.xml";

        Reader reader = null;
        SqlSession sqlSession = null;
        try {
            reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            sqlSession = sqlSessionFactory.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> t = userMapper.findByUserName("a");
            System.out.println(t);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }
}
