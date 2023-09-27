package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberDAO {
	private Connection getConnection() {
		Connection conn = null;
		String driver = "com.mysql.cj.jdbc.Driver";
		try {
			Class.forName(driver);
			
			String dbname = "javaweb";	
			String url = "jdbc:mysql://localhost:3306/"+dbname+"?ServerTimezone=UTC";
			String user = "root";
			String password = "rpass";
			
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void close(Connection conn, PreparedStatement pstmt) {
		try {
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public List<MemberDTO> getMemberList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		try {
			conn = getConnection();
			
			String sql = "select * from member order by idx desc";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int idx = rs.getInt("idx");
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String role = rs.getString("role");
				Date regdate = rs.getDate("regdate");
				
				MemberDTO dto = new MemberDTO(idx, id, pw, name, role, regdate);
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	} // End
	
	public MemberDTO getMember(MemberDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDTO retDto = null;
		
		try {
			conn = getConnection();
			
			String sql = "select idx, id, pw, name, role, regdate from member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int idx = rs.getInt("idx");
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String role = rs.getString("role");
				Date regdate = rs.getDate("regdate");
				System.out.println(regdate);
				retDto = new MemberDTO(idx, id, pw, name, role, regdate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return retDto;
	} // End
	
	public int insert(MemberDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		
		try {
			conn = getConnection();
			
			String sql = "insert into member(id, pw, name, role) values (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getRole());
			
			rs = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		return rs;
	} // End
	
	public int update(MemberDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		
		try {
			conn = getConnection();
			
			String sql = "update member set pw = ?, name = ?, role = ? where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getRole());
			pstmt.setString(4, dto.getId());
			
			rs = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		return rs;
	}
	
	public int delete(MemberDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		
		try {
			conn = getConnection();
			
			String sql = "delete from member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			
			rs = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		return rs;
	}
	
	public List<MemberDTO> getMemberSearchNameList(MemberDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		try {
			conn = getConnection();
			
			String sql = "select idx, id, pw, name, role, regdate from member where name like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + dto.getName() + "%");
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int idx = rs.getInt("idx");
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String role = rs.getString("role");
				Date regdate = rs.getDate("regdate");
				
				dto = new MemberDTO(idx, id, pw, name, role, regdate);
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	} // End
}
