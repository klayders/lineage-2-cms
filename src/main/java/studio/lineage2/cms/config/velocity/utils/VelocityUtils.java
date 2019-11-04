package studio.lineage2.cms.config.velocity.utils;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;
import java.util.Map;

public class VelocityUtils {

  public static String mergeTemplate(String templateName, Map<String, Object> model) {
    VelocityContext context = new VelocityContext();
    if (model != null) {
      model.forEach(context::put);
    }
    Template template = Velocity.getTemplate(templateName);
    StringWriter sw = new StringWriter();
    template.merge(context, sw);
    return sw.toString();
  }

}
