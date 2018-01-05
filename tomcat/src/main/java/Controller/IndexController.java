package Controller;

import Service.TestService;
import Service.TestService2;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * Created by Younian on 2016/3/14.
 */
@Controller
@ResponseBody
public class IndexController {


    @RequestMapping(value = "/redis", method = RequestMethod.GET)
    public String redis(HttpServletRequest request, Map<String, Object> model) {

        Set sentinels = new HashSet();
        sentinels.add(new HostAndPort("10.1.200.76", 26379).toString());
        sentinels.add(new HostAndPort("10.1.200.27", 26379).toString());
        sentinels.add(new HostAndPort("10.1.200.78", 26379).toString());
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        JedisSentinelPool sentinelPool = new JedisSentinelPool("alpha-portal-redis", sentinels, config, 100, null, 0);
        System.out.println("Current master: " + sentinelPool.getCurrentHostMaster().toString());

        Jedis master = sentinelPool.getResource();
        master.set("username", "123123123");
        sentinelPool.returnResource(master);
        System.out.println(master.get("username"));

        BigDecimal a = new BigDecimal(1);
        BigDecimal b = BigDecimal.ZERO;
        System.out.println(a.divide(b).doubleValue());
        return "index";
    }

}
