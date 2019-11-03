package studio.lineage2.cms.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "items_log")
@Data
public class ItemLog {
  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @Column(name = "id")
  private long id;
  @Column(name = "m_account_id")
  private long mAccountId;
  @Column(name = "item_id")
  private int itemId;
  @Column(name = "item_count")
  private long itemCount;
  @Column(name = "text")
  private String text;
}
