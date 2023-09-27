package membermvcjstl;

import java.util.List;
import member.MemberDTO;

public interface IMemberService {

	List<MemberDTO> getMemberList();
	MemberDTO getMember(MemberDTO dto);
	int insert(MemberDTO dto);
	int update(MemberDTO dto);
	int delete(MemberDTO dto);
	List<MemberDTO> getMemberSearchNameList(MemberDTO dto);

}
