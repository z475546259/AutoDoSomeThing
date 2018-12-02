package util;


import cainiaolicai.RecordToFile;
import com.zzq.springcloud.entities.cnUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlManager {
    static PreparedStatement pstm = null;
    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection(){
        Connection connection = null;
        String url = "jdbc:mysql://120.77.202.205:3306/zzq?useSSL=false";
        String user="writer";
        String password="zzqwriter";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection =  DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 获取菜鸟理财用户
     * @return
     */
    public static List<cnUser> getCNUsers(){
        List<cnUser> cnUsers = new ArrayList<>();
        Connection connection = getConnection();
        Statement stmt = null;
        String sql = "select * from app_autodo_result  where is_del = 0 and app_name='菜鸟理财' ";
        try {
            stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(sql);
            while (result.next()) {
                cnUser cnUser = new cnUser();
                cnUser.setTelephone(result.getString("APP_USERTEL"));
                cnUser.setPassword(result.getString("APP_USERPASSWORD"));
                cnUser.setDeviceID(result.getString("DEVICE_ID"));
                cnUser.setUser_agent(result.getString("USER_AGENT"));
                cnUser.setEarn(result.getInt("EARN"));
                cnUser.setScore(result.getInt("APP_USERSCORE"));
                cnUser.setCnuserID(result.getString("APP_USERID"));
                cnUser.setCodeMine(result.getString("CODE_MINE"));
                cnUsers.add(cnUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                RecordToFile.record(new String[]{"断开数据库连接失败",e.toString()}, "error-log.txt",true);
            }
        }
        return  cnUsers;
    }


    public static void updateCnUser(cnUser user){
        Connection connection = null;
        String sqlStr = "select count(1) count from APP_AUTODO_RESULT where app_name =? and app_usertel = ? ";
        try {
            // 执行插入数据操作
            connection = getConnection();
            pstm = connection.prepareStatement(sqlStr);
            pstm.setString(1, "菜鸟理财");
            pstm.setString(2, user.getTelephone());
//            pstm.setString(3, appUserTel);
//            pstm.setString(4, appUserEmail);
//            pstm.setString(5, appUserPassword);
//            pstm.setString(6, appUserId);
            System.out.println(pstm.toString());
            // 创建一个结果集对象
            ResultSet rs = null;
            rs = pstm.executeQuery();
            int count = 0;
            while (rs.next()){
                count =  rs.getInt("count");
            }
            if(count==0){
                AddData( "菜鸟理财", user);
            }else if(count!=0){
                updateCaiNiaoLiCaiData( "菜鸟理财", user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void AddData(String appName,cnUser user) {
        Connection connection = getConnection();
        String sqlStr = "insert into APP_AUTODO_RESULT (app_name,app_username,app_usertel,app_useremail,app_userpassword,app_userscore,app_userid,device_id,user_agent)values(?,?,?,?,?,?,?,?,?)";
        try {
            pstm = connection.prepareStatement(sqlStr);
            pstm.setString(1, appName);
            pstm.setString(2, user.getUser_name());
            pstm.setString(3, user.getTelephone());
            pstm.setString(4, "email");
            pstm.setString(5, user.getPassword());
            pstm.setInt(6, user.getScore());
            pstm.setString(7, user.getCnuserID());
            pstm.setString(8, user.getDeviceID());
            pstm.setString(9, user.getUser_agent());
//            pstm.executeUpdate();
            pstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void updateCaiNiaoLiCaiData(String appName,cnUser user) {
        Connection connection = getConnection();
        String sqlStr = "UPDATE APP_AUTODO_RESULT set app_userscore=? , device_id=? , user_agent=? ,earn=?,app_userid=? ,code_mine=? where app_name= ? and app_usertel =?";
        try {
            pstm = connection.prepareStatement(sqlStr);
            pstm.setInt(1, user.getScore());
            pstm.setString(2, user.getDeviceID());
            pstm.setString(3, user.getUser_agent());
            pstm.setInt(4, user.earn);
            pstm.setString(5, user.getCnuserID());
            pstm.setString(6, user.getCodeMine());
            pstm.setString(7, appName);
            pstm.setString(8, user.getTelephone());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
