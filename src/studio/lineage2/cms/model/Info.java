package studio.lineage2.cms.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.TimeZone;


@Entity
@Table(name = "info")
@Data
public class Info {
  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @Column(name = "id")
  private long id;

  @Column(name = "title")
  private String title;
  @Column(name = "content", length = 100000)
  private String content;
  @Column(name = "image", length = 100000)
  private byte[] image;
  @Column(name = "link")
  private String link;
  @Column(name = "date")
  private long date;

  public String getDateS() {
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    format.setTimeZone(TimeZone.getTimeZone("GMT+3"));
    return format.format(date);
  }
}
