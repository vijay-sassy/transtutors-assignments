package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginValidator")
public class LoginValidator extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    String username = request.getParameter("user");
    String password = request.getParameter("pass");
    
    String[] data = new String[4];
    data[0] = "tstark,FEman,1000000000,0";
    data[1] = "bwidow,gunsgunsgunsguns,420,1337.37";
    data[2] = "spiderman,doeswhateveraspidercan,5.92,12";
    data[3] = "rastley,NEVERGONNAtellaLIE";
    int cnt = -1;
    for(String d:data) {
      ++cnt;
    	if(username.equals(data[cnt].split(",")[0]) &&
    	   password.equals(data[cnt].split(",")[1])) {
    	getServletContext().setAttribute("uname", data[cnt].split(",")[0]);
    	getServletContext().setAttribute("chekbal", data[cnt].split(",")[2]);
    	getServletContext().setAttribute("savbal", data[cnt].split(",")[3]);
    	RequestDispatcher dis = request.getRequestDispatcher("accounts.jsp");
        dis.forward(request, response);
    	break;
       } 
    }
    out.println("Invalid Credentials");
  }
}
