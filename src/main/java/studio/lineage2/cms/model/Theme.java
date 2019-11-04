package studio.lineage2.cms.model;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

@Data
public class Theme {


  private int topicId;
  private String titleFull;
  private String title;
  private String date;
  private int userId;
  private String name;

  public Theme(int topicId, String title, int date, int userId, String name) {
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    format.setTimeZone(TimeZone.getTimeZone("GMT+3"));

    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(date * 1000L);

    this.topicId = topicId;
    this.titleFull = title;
    this.title = title.length() > 25 ? title.substring(0, 25) + "..." : title;
    this.date = format.format(calendar.getTime());
    this.userId = userId;
    this.name = name;
  }
}
