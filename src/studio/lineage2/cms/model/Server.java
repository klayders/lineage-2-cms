package studio.lineage2.cms.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "server")
@Data
public class Server {
  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @Column(name = "id")
  private long id;

  @Column(name = "type")
  private ServerType type;

  @Column(name = "loginId")
  private long loginId;

  @Column(name = "name")
  private String name;

  @Column(name = "ip")
  private String ip;

  @Column(name = "port")
  private String port;

  @Column(name = "login")
  private String xmlRpcL;

  @Column(name = "password")
  private String xmlRpcP;

  @Column(name = "enable")
  private boolean enable;
}
