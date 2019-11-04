package studio.lineage2.cms.config.velocity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Data
@ConfigurationProperties(prefix = "velocity.model")
public class VelocityModelAttributes {
  private String mediahostingUrl;
  private String adminEntityControllerUrl;

  public Map<String, Object> toMap() {
    return Map.of(
      "mediahostingUrl", mediahostingUrl,
      "adminEntityControllerUrl", adminEntityControllerUrl
    );
//    return Json.json(this).as(Map.class, String.class, Object.class);
  }
}
