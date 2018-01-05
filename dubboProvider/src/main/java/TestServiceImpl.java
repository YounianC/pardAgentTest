import Service.TestService;
import redis.clients.jedis.Jedis;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/7/11.
 */
public class TestServiceImpl implements TestService {

    private Map<Integer, Integer> map = new HashMap<>();
    private AtomicInteger total = new AtomicInteger(0);

    @Override
    public String getData(int count) {
        /*long before = System.currentTimeMillis();
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("sentinel01.main.redis.host");
        jedis.auth("app84f29e0e67461159e13f");
        //System.out.println("连接成功");
        jedis.getClient();
        //查看服务是否运行
        //System.out.println("服务正在运行: " + jedis.ping());

        for (int i = 0; i < 100; i++) {
            jedis.set("key", "value" + i);
            jedis.get("key");
        }
        //System.out.println(jedis.get("key"));
        long after = System.currentTimeMillis();
        //System.out.println(after - before);


        Connection con = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/blog", "root", "root");
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from `t_admin`");
            *//*stmt.execute("select * from `t_admin`");
            stmt.execute("select * from `t_admin`");*//*
            while (rs.next()) {
                long id = rs.getLong("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ignored) {
            }
        }*/


        return "DATA:" + new Date().toString();
    }

    @Override
    public String getData2(int count) {
        return "getData2";
    }
}
