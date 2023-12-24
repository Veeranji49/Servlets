

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
@WebServlet("/Insert1")

public class Insert1 extends HttpServlet {
	

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int num=Integer.parseInt(request.getParameter("num"));
		String name=request.getParameter("name");
		int Networth=Integer.parseInt(request.getParameter("Networth"));
		
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
		
			String query="insert into account values(?,?,?)";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, num);
			ps.setString(2, name);
			ps.setInt(3, Networth);
			int count=ps.executeUpdate();
			if(count>0)
			{
				pw.println("Record inserted successfully");
			}
			else
			{
				pw.println("Record failed in insertion");
			}
			
		}
		catch(Exception e)
		{
			pw.println("Exception:"+e.getMessage());
		}
		finally
		{
			if(con!=null)
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
}
