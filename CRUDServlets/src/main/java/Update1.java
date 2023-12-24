

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Update1")
public class Update1 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int ids=Integer.parseInt(request.getParameter("ids"));
		
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
			String query="select * from bashleage where ids=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, ids);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				pw.println("<html>");
				pw.println("<body>");
				pw.println("<form action='Update1' method='post'>");
				pw.println("Enter ids:");
				pw.println("<input type='text' value='"+rs.getInt(1)+"' name='ids'/>");
				pw.println("<br/>");
				pw.println("<input type='text' value='"+rs.getString(2)+"' name='teams'/>");
				pw.println("<br/>");
				pw.println("<input type='text' value='"+rs.getInt(3)+"' value='networth'/>");
				pw.println("<br/>");
				pw.println("<input type='submit' value='update'/>");
				pw.println("</form>");
				pw.println("</body>");
				pw.println("</html>");
			}
			else
			{
				pw.println("Exception:Invalid ");
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
				catch(Exception e)
				{
					
				}
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int ids=Integer.parseInt(request.getParameter("ids"));
		String teams=request.getParameter("teams");
		int networth=Integer.parseInt(request.getParameter("networth"));
		
		String driver="oracle.jdbc.driver.OracleDriver";
		Connection con=null;
		PrintWriter pw=response.getWriter();
		
		try
		{
			Class.forName(driver);
			
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String uname="system";
			String pwd="Veeranji";
			
			con=DriverManager.getConnection(url,uname,pwd);
			String query="update bashleage set teams=? where ids=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1,ids);
			ps.setString(2, teams);
			int count=ps.executeUpdate();
			if(count>0)
			{
				pw.println("<h2>Record updated successfully</h2>");
			}
			else
			{
				pw.println("<h2>Record failed in updation");
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
				catch(Exception e)
				{
		
				}
			}
		}
	}

}
