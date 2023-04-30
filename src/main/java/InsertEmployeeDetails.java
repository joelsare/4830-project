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
@WebServlet("/InsertEmployeeDetails")
public class InsertEmployeeDetails extends HttpServlet {
    private static final long serialVersionUID = 1;

   // String dns = "ubunrds1.cmbclqluu3td.us-east-2.rds.amazonaws.com";


    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertEmployeeDetails() {
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
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phonenumber = request.getParameter("phonenumber");
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String title = "Insert Employee Details into database";
        String docType =
            "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor = \"##CCCCFF\">\n" + //
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
        } catch (SQLException e2) {
            // TODO Auto-generated catch block
            System.out.println("Connection Failed!:\n" + e2.getMessage());
        }
        System.out.println("SUCCESS!!!! You made it, take control     your database now!");
        System.out.println("Creating statement...");

        sql = "insert into myTable (MYUSER,EMAIL,PHONE) values(?,?,?);";

        try {

            statement1 = connection.prepareStatement(sql);
            String theUserName = username;
            String emailval = email;
            String phoneval = phonenumber;
            statement1.setString(1, theUserName);
            statement1.setString(2, emailval);
            statement1.setString(3, phoneval);

        } catch (SQLException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        try {

            statement1.executeUpdate();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        out.println("Thank you for registering your details");
        out.println("</body></html>");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
