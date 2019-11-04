package studio.lineage2.cms.config.velocity.utils;

import java.util.Date;

public class TimeTool {
  @SuppressWarnings("deprecation")
  public String formatTime(Date date) {
    int hours = date.getHours();
    int minutes = date.getMinutes();
    return baseZero(hours) + ":" + baseZero(minutes);
  }

  protected static String baseZero(int value) {
    return (value < 10 ? 0 + "" + value : "" + value);
  }
}
