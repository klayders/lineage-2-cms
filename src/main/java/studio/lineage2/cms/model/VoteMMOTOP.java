package studio.lineage2.cms.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "vote_mmotop")
@Data
public class VoteMMOTOP {
  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @Column(name = "id")
  private long id;
  @Column(name = "vote_id")
  private long voteId;
  @Column(name = "name")
  private String name;
  @Column(name = "take")
  private boolean take;
}
