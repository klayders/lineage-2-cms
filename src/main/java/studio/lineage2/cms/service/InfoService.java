package studio.lineage2.cms.service;

import org.springframework.stereotype.Service;
import studio.lineage2.cms.model.Info;
import studio.lineage2.cms.repository.InfoRepository;

import java.util.List;

@Service
public class InfoService {
  private final InfoRepository infoRepository;

  public InfoService(InfoRepository infoRepository) {
    this.infoRepository = infoRepository;
  }

  public List<Info> findAll() {
    return infoRepository.findAll();
  }

  public Info findOne(long id) {
    return infoRepository.getOne(id);
  }

  public void save(Info info) {
    infoRepository.save(info);
  }

  public void delete(long infoId) {
    infoRepository.deleteById(infoId);
  }
}
