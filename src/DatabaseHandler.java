import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler {
    public Connection getConnection(){
        Connection connection=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_transaction","root","qazWSX1@");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public  ArrayList<User> selectAllUser(){
        ArrayList<User> userList= new ArrayList<>();
        Connection conn=getConnection();
        String sqlStatement="select * from users";
        Statement statement= null;
        try {
            statement = conn.createStatement();
            ResultSet rs= statement.executeQuery(sqlStatement);

            while (rs.next()){
                User user=new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5));
                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    public  User selectUser(String username){
        Connection conn=getConnection();
        String sqlStatement="select * from users where username=?";
        PreparedStatement preparedStatement=null;
        User user=null;
        try {
            preparedStatement=conn.prepareStatement(sqlStatement);
            preparedStatement.setString(1,username);
            ResultSet rs=preparedStatement.executeQuery();
            while(rs.next()){
                user=new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public User selectUserById(int id){
        Connection conn=getConnection();
        String sqlStatement="select * from users where id=?";
        PreparedStatement preparedStatement=null;
        User user=null;
        try {
            preparedStatement=conn.prepareStatement(sqlStatement);
            preparedStatement.setInt(1,id);
            ResultSet rs=preparedStatement.executeQuery();
            while(rs.next()){
                user=new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public void updateMinusAmount(Connection conn,double amountTransfer, int updateId,double userAmount) throws SQLException {
//        Connection conn=getConnection();
        String sqlStatement="update users set amount=(? - ?) where id=?";
        PreparedStatement preparedStatement=null;


            preparedStatement=conn.prepareStatement(sqlStatement);
            preparedStatement.setDouble(1,userAmount);
            preparedStatement.setDouble(2,amountTransfer);
            preparedStatement.setDouble(3,updateId);

            preparedStatement.executeUpdate();


    }

    public void insertUser(User user) throws SQLException {
        Connection connection = getConnection();
        String sqlStatment= "insert into users(name,username,password,amount) value(?,?,?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(sqlStatment);
        preparedStatement.setString(1,user.getName());
        preparedStatement.setString(2,user.getUsername());
        preparedStatement.setString(3,user.getPassword());
        preparedStatement.setDouble(4,user.getAmount());

        preparedStatement.executeUpdate();
    }

    public void updatePlusAmount(Connection conn,double amountTransfer, int updateId,double userAmount) throws SQLException {
//        Connection conn=getConnection();
        String sqlStatement="update users set amount=(? + ?) where id=?";
        PreparedStatement preparedStatement=null;


            preparedStatement=conn.prepareStatement(sqlStatement);
            preparedStatement.setDouble(1,userAmount);
            preparedStatement.setDouble(2,amountTransfer);
            preparedStatement.setDouble(3,updateId);

            preparedStatement.executeUpdate();


    }
//    public static void main(String[] args) {
//       User user=selectUserById(1);
//       updateAmount(500,user.getId(),user.getAmount());
//    }
}

