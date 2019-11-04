package studio.lineage2.cms.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "items")
@Data
public class Item {
  @Id
  private long id;
  @Column(name = "owner_id")
  private long ownerId;
  @Column(name = "item_id")
  private int itemId;
  // количество одного предмета
  @Column(name = "amount")
  private long itemCount;


  private ItemLocation itemLocation;
  @Column(name = "item_type")
  private long itemType;
  private int slot;
  private int enchant;

  public void incCount(long count) {
    itemCount += count;
  }

  public void decCount(long count) {
    itemCount -= count;
  }
}
