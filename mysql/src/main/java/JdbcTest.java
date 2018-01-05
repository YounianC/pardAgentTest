import java.sql.*;

/**
 * Created by Administrator on 2017/7/11.
 */
public class JdbcTest {

    public static void main(String[] args) {
        while (true) {
                test();
        }
    }

    public static void test() {
        Connection con = null;
        Statement stmt = null;

        try {
            // 1、加载MYSQL驱动，这是`Driver`的实现，MySQL的JDBC驱动类是com.mysql.jdbc.Driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            // 2、连接到MYSQL，通过`DriverManger`来操作`Driver`，获取数据库连接
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/blog", "root", "root");

            // 3、创建用以执行SQL语言的声明
            stmt = con.createStatement();

            // 4、执行SQL，获取结果
            ResultSet rs = stmt.executeQuery("select * from `t_admin`");

            // 5、遍历并解析结果
            while (rs.next()) {
                long id = rs.getLong("id");
                // ...
            }
        } catch (Exception e) {
            // 如果有异常，进行异常处理
            System.out.print("MYSQL ERROR:" + e.getMessage());

        } finally {
            // 6、关闭连接与声明
            try {
                if (stmt != null) {
                    stmt.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (SQLException ignored) {

            }
        }
    }
}
