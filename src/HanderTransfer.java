import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "HanderTransfer",urlPatterns = "/transfer")
public class HanderTransfer extends HttpServlet {
    public DatabaseHandler dbHandler=new DatabaseHandler();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double amount=Double.parseDouble(request.getParameter("amount")) ;
        User fromUser=(User)request.getSession().getAttribute("loginUser");
        User toUser=(User)request.getSession().getAttribute("transferUser");
        System.out.println(toUser.getName());
        String url="/index.jsp";
        Connection connection=dbHandler.getConnection();
        try {
            connection.setAutoCommit(false);

            dbHandler.updateMinusAmount(connection,amount,fromUser.getId(),fromUser.getAmount());
            dbHandler.updatePlusAmount(connection,amount,toUser.getId(),toUser.getAmount());

            connection.commit();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            if (connection!=null) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        getServletContext().getRequestDispatcher(url).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
