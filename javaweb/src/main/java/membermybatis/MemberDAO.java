package membermybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MemberDAO {

	// Mybtis
	SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession(); 
	SqlSession sqlSession = sqlSessionFactory.openSession(true); // true : AutoCommit

	public List<MemberDTO> getMemberList() {
		
		return sqlSession.selectList("member.getMemberList");
	} // End

	public MemberDTO getMember(MemberDTO dto) {
		
		return sqlSession.selectOne("member.getMember",dto);
	} // End

	public int insert(MemberDTO dto) {
		
		return sqlSession.insert("member.insert",dto);
	} // End

	public int update(MemberDTO dto) {
		
		return sqlSession.update("member.update",dto);
	}

	public int delete(MemberDTO dto) {
		
		return sqlSession.update("member.delete",dto);
	}

	public List<MemberDTO> getMemberSearchNameList(MemberDTO dto) {
		
		return sqlSession.selectList("member.getMemberSearchNameList");
	} // End
}
