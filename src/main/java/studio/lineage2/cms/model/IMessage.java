package studio.lineage2.cms.model;

import com.google.gson.Gson;
import lombok.Data;

@Data
public class IMessage {
  private Type type;
  private String message;

  public IMessage(Type type) {
    this.type = type;
    this.message = "";
  }

  public IMessage(Type type, String message) {
    this.type = type;
    this.message = message;
  }

  public <T> T getData(Class<T> type) {
    Gson gson = new Gson();
    return gson.fromJson(message, type);
  }

  public enum Type {
    SUCCESS,
    FAIL
  }
}
