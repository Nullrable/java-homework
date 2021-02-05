package com.lsd.test.dynmic.source.config.redis;

import com.lsd.test.dynmic.source.dto.RedisConfig;
import com.lsd.test.dynmic.source.remote.RedisConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class RedisSummoner {

    private static final String BEAN_KEY_PREFIX = "_redis_datasource";


    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Autowired
    private RedisConfigService redisConfigService;


    public RedisTemplate getRedisTemplate(String tenantId){

        RedisConfig cfg = redisConfigService.read(tenantId);

        Assert.notNull(cfg, String.format("tenantId[%s]redis config not null", tenantId));

        if(cfg.getDatabase() == null){
            Assert.notNull(cfg, String.format("tenantId[%s]redis database not null", tenantId));
        }

        String beanId = tenantId + "_" + cfg.getDatabase() + BEAN_KEY_PREFIX;


       if(!applicationContext.containsBean(beanId)){

           DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();

           BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(RedisTemplate.class);
           builder.getBeanDefinition().setAttribute("id", beanId);
           builder.addPropertyValue("connectionFactory", createJedisConnectionFactory(cfg));

           beanFactory.registerBeanDefinition(beanId, builder.getBeanDefinition());
       }

       return (RedisTemplate)applicationContext.getBean(beanId);

   }


   private JedisConnectionFactory createJedisConnectionFactory( RedisConfig cfg ){




       RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
       configuration.setHostName(cfg.getHost());
       configuration.setDatabase(cfg.getDatabase());
       configuration.setPort(cfg.getPort());
       configuration.setPassword(cfg.getPassword());

       JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory(configuration);

       return redisConnectionFactory;
   }
}
