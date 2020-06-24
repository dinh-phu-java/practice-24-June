import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "InsertServlet",urlPatterns = "/insert-user")
public class InsertServlet extends HttpServlet {
    private DatabaseHandler dbHandler=new DatabaseHandler();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection=dbHandler.getConnection();

        String name=request.getParameter("name");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        double amount=Double.parseDouble(request.getParameter("amount")) ;
        User phu=dbHandler.selectUser("dinhphu");
        User newUser=new User(name,username,password,amount);
        try {
            connection.setAutoCommit(false);
            dbHandler.updatePlusAmount(connection,300,phu.getId(),phu.getAmount());
            dbHandler.insertUser(newUser);

            User userNew = dbHandler.selectUser(username);
            dbHandler.updateMinusAmount(connection,300,userNew.getId(),newUser.getAmount());
            connection.commit();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            if (connection!=null) {
                try {
                    connection.rollback();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
