

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
@WebServlet("/delete3")
public class delete3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int id=Integer.parseInt(request.getParameter("id"));
		
		String driver="oracle.jdbc.driver.OracleDriver";
		Connection con=null;
		PrintWriter pw=response.getWriter();
		
		try
		{
			Class.forName(driver);
			pw.println("driver loaded");
			
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String uname="system";
			String pwd="Veeranji";
			
			con=DriverManager.getConnection(url,uname,pwd);
			pw.println("Connection is established");
			String query="delete from prasanna where id=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, id);
			int count=ps.executeUpdate();
			if(count>=0)
			{
				pw.println("Record delete successfully");
			}
			else
			{
				pw.println("Record failed in deletion");
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
