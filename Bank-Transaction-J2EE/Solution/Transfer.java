package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Transfer")
public class Transfer extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      PrintWriter out = response.getWriter();
      String from = request.getParameter("from");
      String to = request.getParameter("to");
      double amount = Double.parseDouble(request.getParameter("amt"));
      
      if(amount < 0) {
        out.println("Invalid amount");
      } else {
    	if(from.equals("Checking Account")) {
    	  double chk = Double.parseDouble((String)(getServletContext().getAttribute("chekbal"))) - amount;
    	  double sav = Double.parseDouble((String)(getServletContext().getAttribute("savbal"))) + amount;
    	  request.setAttribute("updchk", chk);
    	  request.setAttribute("updsav", sav);
    	} else {
      	  double sav = Double.parseDouble((String)(getServletContext().getAttribute("savbal"))) - amount;
      	  double chk = Double.parseDouble((String)(getServletContext().getAttribute("chekbal"))) + amount;
      	  chk = chk - amount/100;
    	  request.setAttribute("updchk", chk);
    	  request.setAttribute("updsav", sav);
    	}
    	request.setAttribute("amount", amount);
    	request.setAttribute("from", from);
    	request.setAttribute("to", to);
    	RequestDispatcher dis = request.getRequestDispatcher("result.jsp");
        dis.forward(request, response);
      }
	}

}
