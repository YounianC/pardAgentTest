package com.test.controller;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/approval")
public class ApprovalManageController {
	static {
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
	}
}