package membermvc;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberDAO;
import member.MemberDTO;

@WebServlet("*.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		action(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		action(request, response);
	}
	
	protected void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("action");
		String uri = request.getRequestURI();
		System.out.println(uri); // /myweb/memberList.do
		String action = uri.substring(uri.lastIndexOf("/"));
		
		HttpSession session = request.getSession();
		String view = "";
		
		if(action.equals("/main.do")) {
			System.out.println("/main.do");
			
			view = "main.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}else if(action.equals("/memberList.do")) {
			System.out.println("/memberList.do");
			
			MemberDAO dao = new MemberDAO();
			List<MemberDTO> list = dao.getMemberList();
			
			request.setAttribute("list", list);
			
			view = "memberList.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
			
		}else if(action.equals("/join.do")) {			
			System.out.println("/join.do");
			
			view = "join.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}else if(action.equals("/login.do")) {
			System.out.println("/login.do");
			
			view = "login.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
			
		}else if(action.equals("/joinAction.do")) {
			System.out.println("/joinAction.do");
			
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String role = request.getParameter("role");
			MemberDTO dto = new MemberDTO(id, pw, name, role);
			
			MemberDAO dao = new MemberDAO();
			int rs = dao.insert(dto);
			
			view = "login.do";			
			response.sendRedirect(view);
			
		}else if(action.equals("/loginAction.do")) {
			System.out.println("/loginAction.do"); 
			
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			MemberDTO dto = new MemberDTO();
			dto.setId(id);
			
			MemberDAO dao = new MemberDAO();
			dto = dao.getMember(dto);
			
			if (dto != null) {
				if (dto.getPw().equals(pw)) {
					
					session.setAttribute("id", id);
					session.setAttribute("name", dto.getName());
					
					view = "main.do";
				} else {
					view = "login.do";
				}
			} else {
				view = "login.do";
			}
			
			response.sendRedirect(view);
			
		}else if(action.equals("/logout.do")) {
			System.out.println("/logout.do");
			
			session.invalidate();
			
			view = "main.do";
			response.sendRedirect(view);
			
		}else if(action.equals("/update.do")) {
			System.out.println("/update.do");
			
			String id = (String)session.getAttribute("id");

			MemberDTO dto = new MemberDTO();
			dto.setId(id);

			MemberDAO dao = new MemberDAO();
			dto = dao.getMember(dto);
			
			request.setAttribute("dto", dto);
			
			view = "update.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
			
		}else if(action.equals("/updateAction.do")) {
			System.out.println("/updateAction.do");
			
			String id = (String)session.getAttribute("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String role = request.getParameter("role");
			MemberDTO dto = new MemberDTO(id, pw, name, role);
			
			MemberDAO dao = new MemberDAO();
			int rs = dao.update(dto);
			
			session.setAttribute("name", name);
			
			view = "update.do";
			response.sendRedirect(view);
			
		}else if(action.equals("/delete.do")) {			
			System.out.println("/delete.do");
			
			String path = "delete.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
			
		}else if(action.equals("/deleteAction.do")) {			
			System.out.println("/deleteAction.do");
			
			String id = (String)session.getAttribute("id");
			String pw = request.getParameter("pw");
			
			MemberDTO dto = new MemberDTO();
			dto.setId(id);
			MemberDAO dao = new MemberDAO();
			dto = dao.getMember(dto);
			
			if (dto.getPw().equals(pw)) {
				dao.delete(dto);
				session.invalidate();
				
				view = "main.do";
			} else {
				view = "delete.do";
			}
			
			response.sendRedirect(view);			
			
		}
		
	}

}
