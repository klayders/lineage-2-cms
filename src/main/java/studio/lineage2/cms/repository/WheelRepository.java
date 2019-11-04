package studio.lineage2.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studio.lineage2.cms.model.Wheel;

@Repository
public interface WheelRepository extends JpaRepository<Wheel, Long> {
}
