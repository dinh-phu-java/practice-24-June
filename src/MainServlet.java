import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "MainServlet",urlPatterns="/home")
public class MainServlet extends HttpServlet {
    public DatabaseHandler dbHandler=new DatabaseHandler();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username").trim();
        String password=request.getParameter("password").trim();
        HttpSession session=request.getSession();
        session.setMaxInactiveInterval(-1);
        String url="/index.jsp";

        ArrayList<User> userList=new ArrayList<>(dbHandler.selectAllUser());

        boolean findUser=false;
        ArrayList<String> message=new ArrayList<>();

        for (User user:userList){
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                findUser=true;
                break;
            }
        }

        if (findUser){
            User loginUser=dbHandler.selectUser(username);
          session.setAttribute("loginUser",loginUser);
            url="/home.jsp";
        }else{
            url="index.jsp";
            message.add("Can't log in");
        }
        session.setAttribute("userList",userList);
        request.setAttribute("message",message);
        getServletContext().getRequestDispatcher(url).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        HttpSession session=request.getSession();
        int transferId=Integer.parseInt(request.getParameter("id")) ;
        String url="/index.jsp";
        if (action==null){
            action="views";
        }
        switch(action){
            case "transfer":
                url="/transfer.jsp";
                break;
        }
        User transferUser=dbHandler.selectUserById(transferId);



        session.setAttribute("transferUser",transferUser);
        getServletContext().getRequestDispatcher(url).forward(request,response);
    }
}
