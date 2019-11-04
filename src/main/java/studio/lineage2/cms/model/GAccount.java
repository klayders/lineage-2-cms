package studio.lineage2.cms.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@Data
public class GAccount {
  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @Column(name = "id")
  private long id;
  @Column(name = "m_account_id")
  private long mAccountId;
  @Column(name = "server_id")
  private long serverId;
  @Column(name = "name")
  private String name;

  public GAccount(long mAccountId, long serverId, String name) {
    this.mAccountId = mAccountId;
    this.serverId = serverId;
    this.name = name;
  }
}
