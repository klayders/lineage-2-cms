package studio.lineage2.cms.config.velocity;


import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.ToolManager;

import java.io.StringWriter;
import java.util.Map;

@Setter
@RequiredArgsConstructor
public class VelocityViewRender {

  private String prefix = "vm/views/";
  private String suffix = ".vm";
  private final VelocityEngine velocityEngine;
  private final ToolManager toolManager;
  private final AutocompleteVelocityHelper autocompleteVelocityHelper;
  private final Map<String, Object> model;

  public String render(String templateName, Map<String, Object> model) {
    VelocityContext context = new VelocityContext(toolManager.createContext());
    context.put("Autocomplete", autocompleteVelocityHelper);
    if (this.model != null) {
      this.model.forEach(context::put);
    }
    if (model != null) {
      model.forEach(context::put);
    }
    Template template = velocityEngine.getTemplate(prefix + templateName + suffix, "UTF-8");
    StringWriter sw = new StringWriter();
    template.merge(context, sw);
    return sw.toString();
  }
}
