import mapper.RoleMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.Role;
import utils.SqlSessionFactoryUtils;

import java.util.logging.Logger;

public class Main3 {
    private static Logger log = Logger.getLogger(Main3.class.getName());
    public static void main(String[] args){
        SqlSession sqlSession = null;
        try{
            sqlSession = SqlSessionFactoryUtils.openSession();
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            Role role = roleMapper.getRole(1L);
            log.info(role.getRoleName());
            System.out.println(role);
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
    }
}
