package studio.lineage2.cms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import studio.lineage2.cms.model.Server;
import studio.lineage2.cms.repository.ServerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServerService {
  private static final String SERVER_CACHE_NAME = "server";

  private final ServerRepository serverRepository;

  @CacheEvict(value = SERVER_CACHE_NAME, cacheManager = "serverCacheManager", allEntries = true)
  public void save(Server server) {
    serverRepository.save(server);
  }

  @Cacheable(value = SERVER_CACHE_NAME, cacheManager = "serverCacheManager", key = "#id")
  public Server findOne(long id) {
    return serverRepository.findOne(id);
  }

  @Cacheable(value = SERVER_CACHE_NAME, cacheManager = "serverCacheManager", key = "'allServers'")
  public List<Server> findAll() {
    return serverRepository.findAll();
  }

  @CacheEvict(value = SERVER_CACHE_NAME, cacheManager = "serverCacheManager", key = "#id")
  public void delete(long id) {
    serverRepository.delete(id);
  }
}
