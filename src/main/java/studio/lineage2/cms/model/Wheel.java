package studio.lineage2.cms.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "wheel")
@Data
public class Wheel {
  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @Column(name = "id")
  private long id;
  @Column(name = "item_id")
  private int itemId;
  @Column(name = "item_count")
  private long itemCount;
  @Column(name = "chance")
  private int chance;
  @Column(name = "image", length = 100000)
  private byte[] image;
  @Transient
  private String itemName;
}
