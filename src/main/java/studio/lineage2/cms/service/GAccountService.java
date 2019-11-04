package studio.lineage2.cms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studio.lineage2.cms.model.GAccount;
import studio.lineage2.cms.repository.GAccountRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GAccountService {

  private final GAccountRepository gAccountRepository;


  public void save(GAccount user) {
    gAccountRepository.save(user);
  }

  public List<GAccount> findByMAccountIdAndServerId(long ownerId, long serverId) {
    gAccountRepository.findAll().stream()
      .filter(gAccount -> gAccount.getOwnerId() == ownerId && gAccount.getServerId() == serverId)
      .collect(Collectors.toList());
    return gAccountRepository.findAllByOwnerIdAndServerId(ownerId, serverId);
  }
}
