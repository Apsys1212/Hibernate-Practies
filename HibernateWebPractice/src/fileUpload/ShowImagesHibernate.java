package fileUpload;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;

import fileUpload.bean.User;

/**
 * Servlet implementation class ShowImagesHibernate
 */
@WebServlet("/showImage")
public class ShowImagesHibernate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowImagesHibernate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getImage(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getImage(request, response);
	}
	
	public static void getImage(HttpServletRequest request, HttpServletResponse response) {
		try {
			Session session=new AnnotationConfiguration().configure().buildSessionFactory().openSession();
            
			User user=new User();
			
			Query query=session.createQuery("from User");
			@SuppressWarnings("unchecked")
			List<User> list=query.list();
			Iterator<User> iterator=list.iterator();
			while (iterator.hasNext()) {
				user = (User) iterator.next();
				System.out.println(user.getUserName());
			}
			
			HttpSession httpSession=request.getSession();
			httpSession.setAttribute("user", user);
			request.getRequestDispatcher("/Message.jsp").forward(request, response);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
