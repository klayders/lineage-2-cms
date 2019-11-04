package studio.lineage2.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studio.lineage2.cms.model.GAccount;

import java.util.List;


@Repository
public interface GAccountRepository extends JpaRepository<GAccount, Long> {
  List<GAccount> findAllByOwnerIdAndServerId(long ownerId, long serverId);
}
