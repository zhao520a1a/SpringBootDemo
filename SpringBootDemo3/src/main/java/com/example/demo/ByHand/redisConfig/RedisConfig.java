//package com.example.demo.ByHand.redisConfig;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPoolConfig;
//
//
//
///*
// * 手动注入JedisConnectionFactory
// * redis 缓存配置;
// * <p>
// * 缓存主要有几个要实现的类：
// * 其一就是CacheManager缓存管理器；
// * 其二就是具体操作实现类；
// * 其三就是CacheManager工厂类（这个可以使用配置文件配置的进行注入，也可以通过编码的方式进行实现）；
// * 其四就是缓存key生产策略
// * （当然Spring自带生成策略，但是在Redis客户端进行查看的话是系列化的key,对于我们肉眼来说就是感觉是乱码了，这里我们先使用自带的缓存策略）。
// * <p>
// * 注意：RedisCacheConfig这里也可以不用继承 ：CachingConfigurerSupport，也就是直接一个普通的Class就好了；
// * 这里主要我们之后要重新实现 key的生成策略，只要这里修改KeyGenerator，其它位置不用修改就生效了。
// * 若使用普通类的方式的话，那么在使用@Cacheable的时候还需要指定KeyGenerator的名称;这样编码的时候比较麻。
// */
//
//
//@Slf4j
//@Configuration
//@EnableAutoConfiguration  //必须要有
//public class RedisConfig {
//
//    @Value("${spring.redis.database}")
//    private int database;
//
//    @Value("${spring.redis.host}")
//    private String host;
//
//    @Value("${spring.redis.port}")
//    private int port;
//
//    @Value("${spring.redis.timeout}")
//    private int timeout;
//
//    @Value("${spring.redis.password}")
//    private String password;
//
//
//    @Bean
//    @ConfigurationProperties(prefix="spring.redis")
//    public JedisPoolConfig getRedisConfig(){
//        JedisPoolConfig config = new JedisPoolConfig();
//        return config;
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix="spring.redis")
//    public JedisConnectionFactory getConnectionFactory(){
//        JedisConnectionFactory factory = new JedisConnectionFactory();
//        factory.setDatabase(database);
//        factory.setHostName(host);
//        factory.setPassword(password);
//        factory.setPort(port);
//        factory.setTimeout(timeout);
//        factory.setUsePool(true);
//
//        JedisPoolConfig config = getRedisConfig();
//        factory.setPoolConfig(config);
//        log.info("JedisConnectionFactory bean init success.");
//        return factory;
//    }
//
//
//    /**
//     * redis模板操作类,类似于jdbcTemplate的一个类;
//     *
//     * 虽然CacheManager也能获取到Cache对象，但是操作起来没有那么灵活；
//     * 这里在扩展下：RedisTemplate这个类不见得很好操作，我们可以在进行扩展一个我们
//     * 自己的缓存类，比如：RedisStorage类;
//     * @return
//     */
//    @Bean
//    public RedisTemplate<?, ?> getRedisTemplate(){
//        RedisTemplate<?,?> template = new StringRedisTemplate(getConnectionFactory());
//        return template;
//    }
//
//
//
//
//    public static void main(String[] args) {
//        Jedis jedis = new Jedis("10.106.192.33",6379);
//        jedis.auth("redis");
//        System.out.println("Connection to server sucessfully");
//        System.out.println("Server is running: "+jedis.ping());
//    }
//
//
//}