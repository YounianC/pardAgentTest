import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/7/11.
 */
public class TestJedis {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
       /* Jedis jedis = new Jedis("sentinel01.main.redis.host");
        jedis.auth("app84f29e0e67461159e13f");
        System.out.println("连接成功");
        jedis.getClient();
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());

        jedis.set("key", "value");
        System.out.println(jedis.get("key"));*/


        Set sentinels = new HashSet();
        sentinels.add(new HostAndPort("10.1.200.76", 26379).toString());
        sentinels.add(new HostAndPort("10.1.200.27", 26379).toString());
        sentinels.add(new HostAndPort("10.1.200.78", 26379).toString());
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        JedisSentinelPool sentinelPool = new JedisSentinelPool("alpha-portal-redis", sentinels, config, 100 ,null, 0);
        System.out.println("Current master: " + sentinelPool.getCurrentHostMaster().toString());

        Jedis master = sentinelPool.getResource();
        master.set("username","123123123");
        sentinelPool.returnResource(master);
        System.out.print(master.get("username"));
    }
}
