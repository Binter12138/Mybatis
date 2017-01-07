import entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;


public class Test1 {


    /**
     * 根据id进行查询
     * @throws IOException
     */
    @Test
    public void findById() throws IOException {
        String resource = "mybatis_config.xml";

        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //第一个参数：statement的id，等于：映射文件的namespace+"."+ statement的id
        //第二个参数：指定和映射文件中所匹配的parameterType类型的参数
        User user = sqlSession.selectOne("userMapper.getUser", 1);

        System.out.println(user);
        sqlSession.close();



    }


    @Test
    public void findByUserName()
    {
        SqlSession session = null;
        try {
            String resouce = "mybatis_config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resouce);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            session = sessionFactory.openSession();
            List<User> userList = session.selectList("userMapper.findByUserName", "o");

            for (User user : userList) {
                System.out.println(user.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (session != null) {
                session.close();
            }
        }


    }

    /**
     *添加用户
     */
    @Test
    public void insertUser(){

        InputStream resourceAsStream = null;
        SqlSession session = null;

        try {
            String resource = "mybatis_config.xml";
            resourceAsStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            session = sqlSessionFactory.openSession();
            User user = new User();
            user.setName("bb");
            user.setAge(21);
            session.insert("userMapper.insertUser",user);
            System.out.println(user.getId());
            session.commit();

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }



    }




}
