import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// Fetch data
/**
 * Servlet implementation class demo3
 */
@WebServlet("/GetEmployeeDetails")
public class GetEmployeeDetails extends HttpServlet {
    private static final long serialVersionUID = 1 ;

    //String dns = "ubunrds1.cmbclqluu3td.us-east-2.rds.amazonaws.com";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEmployeeDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String sql;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement statement1 = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        String keyword = request.getParameter("keyword");
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String title = "Your Library";
        String docType =
            "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        out.println(docType + //
            "<html>\n" + //
            "<head><link href=\"style.css\" rel=\"stylesheet\" /> <title>" + title + "</title></head>\n" + //
//            "<body bgcolor = \"##CCCCFF\">\n" + //
            "<h1 align = \"center\">" + title + "</h1>\n");


        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        try {
   		     String hostname="34.207.112.189";
   		//	 String port = System.getProperty("RDS_PORT");
   			 String dbname="myDB";
   			 String userName="joel";
   			 String password="chance915";
        	String jdbcUrl = "jdbc:mysql://" + hostname + ":" + 3306 + "/" + dbname + "?user=" + userName + "&password=" + password;
        	connection = DriverManager.getConnection(jdbcUrl);
        	//  connection = DriverManager.getConnection("jdbc:mysql://" + dns + ":3306/myDB1", "admin1", "root");
        } catch (SQLException e2) {
            // TODO Auto-generated catch block
            System.out.println("Connection Failed!:\n" + e2.getMessage());
        }
        System.out.println("SUCCESS!!!! You made it, take control     your database now!");
        System.out.println("Creating statement...");

        sql = "SELECT * FROM myTable WHERE MYUSER LIKE ?";
//        sql = "SELECT * FROM myTable";
        try {
        	System.out.println(keyword);
            statement1 = connection.prepareStatement(sql);
            String theUserName = keyword;
            statement1.setString(1, theUserName);
     
        } catch (SQLException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        try {

            rs = statement1.executeQuery();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        out.println("<table border=1 width=50% height=30%>");
        out.println("<tr><th>Image</th><th>EmpId</th><th>EmpName</th><th>Email</th><th>Phone Number</th>");
        try { 
            while (rs.next()) {
                //Retrieve by column name
                String id = rs.getString("ID");
                String username = rs.getString("MYUSER");
                String email = rs.getString("EMAIL");
                String phone = rs.getString("PHONE");
                String picture = "data:image/jpeg;base64,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";
                out.println("<tr><td> <img src=\"" + picture + "\">"+" </td> <td>" + id + "</td><td>" + username + "</td><td>" + email + "</td><td>" + phone + "</td></tr>");
//                out.println("<img src=\"" + picture + "\">");
            }
            out.println("</body></html>");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
