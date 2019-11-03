package studio.lineage2.cms.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Eanseen
 * 04.11.2015
 */
@Entity
@Table(name = "items_log")
public class ItemLog {
  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @Column(name = "id")
  private @Getter
  @Setter
  long id;
  @Column(name = "m_account_id")
  private @Getter
  @Setter
  long mAccountId;
  @Column(name = "item_id")
  private @Getter
  @Setter
  int itemId;
  @Column(name = "item_count")
  private @Getter
  @Setter
  long itemCount;
  @Column(name = "text")
  private @Getter
  @Setter
  String text;
}
