package redis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtils {
  
	
	private static JedisPool pool = null;
	
	static {
		//加载配置文件
		InputStream in = JedisPoolUtils.class.getClassLoader().getResourceAsStream("redis.properties");
		Properties pro = new Properties();
		try {
			pro.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
    	//池子的配置对象
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(Integer.parseInt(pro.getProperty("redis.maxIdle").toString()));//最大闲置个数
		poolConfig.setMinIdle(Integer.parseInt(pro.getProperty("redis.minIdle").toString()));//最小闲置个数
		poolConfig.setMaxTotal(Integer.parseInt(pro.getProperty("redis.maxTotal").toString()));//最大连接数
		pool = new JedisPool(poolConfig,"192.168.112.128",6379);
	}
	
	//获得jedis资源的方法
	public static Jedis getJedis() {
		return pool.getResource();
		
	}
	
	public static void main(String[] args) {
		Jedis jedis = getJedis();
		System.out.println(jedis.get("xxx"));
	}
}
