

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Insert3")
public class Insert3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	    int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		int networth=Integer.parseInt(request.getParameter("networth"));

		PrintWriter pw=response.getWriter();
		String driver="oracle.jdbc.driver.OracleDriver";
		Connection con=null;
		
		try
		{
			Class.forName(driver);
			pw.println("driver loaded");
			
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String uname="system";
			String pwd="Veeranji";
			
			con=DriverManager.getConnection(url,uname,pwd);
			pw.println("Connection is established");
			String query="insert into prasanna values(?,?,?)";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1,id);
			ps.setString(2,name);
			ps.setInt(3, networth);
			
			int count=ps.executeUpdate();
			if(count>0)
			{
				pw.println("Record Successfully Completed");
			}
			else
			{
				pw.println("Failed in insertion");
			}
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			try
			{
				con.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

}
