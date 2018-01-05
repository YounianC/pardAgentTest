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
public class IndexController2 {

    /*@Autowired
    private TestService2 testService2;

    @RequestMapping(value = "/dubbo2.html", method = RequestMethod.GET)
    public String dubbo2(HttpServletRequest request, Map<String, Object> model) {
        return testService2.getData(1);
    }*/
}
