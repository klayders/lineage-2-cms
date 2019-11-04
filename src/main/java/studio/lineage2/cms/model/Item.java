package studio.lineage2.cms.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Eanseen
 * 04.11.2015
 */
@Entity
@Table(name = "items")
@Data
public class Item {
  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @Column(name = "id")
  private long id;
  @Column(name = "owner_id")
  private long mAccountId;
  @Column(name = "item_id")
  private int itemId;
  //TODO: проверь это поле, скорее всего тут ошибка
  @Column(name = "amount")
  private Long itemCount;

  public void incCount(long count) {
    itemCount += count;
  }

  public void decCount(long count) {
    itemCount -= count;
  }
}
