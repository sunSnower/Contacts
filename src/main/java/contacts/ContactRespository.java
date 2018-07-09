package contacts;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//持久层，能够从数据库中获取contact
@Repository
//使用Repository注解，在组件扫描的时候会被发现，并创建为Spring应用上下文中的bean
public class ContactRespository {
    private SqlSession sqlSession;

    public ContactRespository(){
        try{
            InputStream in = Resources.getResourceAsStream("Mybatis-config.xml");
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(in);
            sqlSession = sqlSessionFactory.openSession();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getUser(){
        int n = sqlSession.selectOne("dao.TuserMapper.queryCount");
        for(int count = 0; count < 100; count++){
            System.out.println(n);
        }
        return n;
    }

    //查询联系人
    public List<Contact> findAll(){
        return sqlSession.selectList("dao.TuserMapper.findAll");
    }

    //插入联系人
    public void save(Contact contact){
        sqlSession.insert("dao.TuserMapper.addContact");
    }
    public void close(){
        if(sqlSession != null){
            sqlSession.close();
        }
    }
}
