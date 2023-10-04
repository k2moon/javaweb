package membermybatisinterface;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public interface IMemberDAO {	

	public List<MemberDTO> getMemberList();	

	public MemberDTO getMember(MemberDTO dto);

	public int insert(MemberDTO dto);

	public int update(MemberDTO dto);

	public int delete(MemberDTO dto);

	public List<MemberDTO> getMemberSearchNameList(MemberDTO dto);
	
	public List<MemberDTO> getMemberCount(); 
	
	public List<MemberDTO> getMemberListPasing(); 
	
	
}
