package studio.lineage2.cms.config.velocity.utils;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.Resource;

import java.util.Properties;

public class VelocityProperties implements FactoryBean {

  private Properties properties;

  private Resource[] fileLibs;

  private Resource[] classPathLibs;

  private String path;

  public void setProperties(Properties properties) {
    this.properties = properties;
  }

  public void setFileLibs(Resource[] fileLibs) {
    this.fileLibs = fileLibs;
  }

  public void setClassPathLibs(Resource[] classPathLibs) {
    this.classPathLibs = classPathLibs;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Object getObject() throws Exception {
    StringBuilder sb = new StringBuilder();
    boolean first = true;

    for (Resource resource : fileLibs) {
      first = print(sb, first, resource);
    }

    for (Resource resource : classPathLibs) {
      first = print(sb, first, resource);
    }

    properties.setProperty(VelocityEngine.VM_LIBRARY, sb.toString());
    return properties;
  }

  private boolean print(StringBuilder sb, boolean first, Resource resource) {
    if (!first) {
      sb.append(',');
    }
    sb.append(path);
    sb.append(resource.getFilename());

    return false;
  }

  @SuppressWarnings("unchecked")
  public Class getObjectType() {
    return Properties.class;
  }

  public boolean isSingleton() {
    return false;
  }
}
