package studio.lineage2.cms.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import studio.lineage2.cms.model.Server;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

  @Bean
  public CacheManager serverCacheManager() {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager(
      Server.class.getSimpleName().toLowerCase()
    );
    cacheManager.setCaffeine(caffeineCacheBuilder());
    return cacheManager;
  }

  Caffeine<Object, Object> caffeineCacheBuilder() {
    return Caffeine.newBuilder()
      .initialCapacity(100)
      .maximumSize(500)
      .expireAfterAccess(10, TimeUnit.MINUTES)
      .weakKeys()
      .recordStats();
  }
}
