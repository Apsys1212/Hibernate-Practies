package fileUpload;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import fileUpload.bean.User;
 
@WebServlet("/uploadServlet")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class Controller extends HttpServlet {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 8079147158117495997L;
	// database connection settings
   // private String dbURL = "jdbc:mysql://localhost:3306/hibernatetest";
   // private String dbUser = "root";
   // private String dbPass = "root";
     
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // gets values of text fields
        String firstName = request.getParameter("firstName");
        //String lastName = request.getParameter("lastName");
         
        InputStream inputStream = null; // input stream of the upload file
         
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("photo");
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }
         
        //Connection conn = null; // connection to the database
        String message = null;  // message will be sent back to client
         
        try {
            // connects to the database
            //Class.forName("com.mysql.jdbc.Driver");
           // conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
 
            // constructs SQL statement
          //  String sql = "INSERT INTO user values (?, ?)";
           // PreparedStatement statement = conn.prepareStatement(sql);
           // statement.setString(1, "1");
             
           // if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
            //    statement.setBlob(2, inputStream);
            //}
 
            // sends the statement to the database server
           // int row = statement.executeUpdate();
           // if (row > 0) {
            //    message = "File uploaded and saved into database";
           // }
        	
        	byte[] image = IOUtils.toByteArray(inputStream);
        	Session session=new AnnotationConfiguration().configure().buildSessionFactory().openSession();
        	Transaction tx=session.beginTransaction();
        	
        	User user=new User();
        	user.setUserName(firstName);
        	user.setUpfile(image);
        	session.save(user);
        	tx.commit();
        	if(tx.wasCommitted()){
        		message="File uploaded and saved into database";
        	}
        	session.close();
        	
        } catch (Exception ex) {
            message = "ERROR: " + ex.getMessage();
            ex.printStackTrace();
        } finally {
/*            if (conn != null) {
                // closes the database connection
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
*/            // sets the message in request scope
            request.setAttribute("Message", message);
             
            // forwards to the message page
            getServletContext().getRequestDispatcher("/Message.jsp").forward(request, response);
        }
    }
}