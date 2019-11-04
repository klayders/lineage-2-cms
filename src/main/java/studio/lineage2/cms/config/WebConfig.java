package studio.lineage2.cms.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import studio.lineage2.cms.config.velocity.VelocityModelAttributes;
import studio.lineage2.cms.interceptor.SiteInterceptor;

import java.util.Locale;


@Configuration
//@PropertySource(value = "file:./application.properties", ignoreResourceNotFound = true)
//@PropertySource(value = "file:./public/application.properties", ignoreResourceNotFound = false)
@EnableWebMvc
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
  private final SiteInterceptor siteInterceptor;
  private final ResourceLoader resourceLoader;
  private final VelocityModelAttributes velocityProperties;

//  @Bean
//  public VelocityViewRender velocityViewRender() throws Exception {
//    var toolManager = new ToolManager();
//    try (InputStream is = resourceLoader.getResource("classpath:templates/toolbox.xml").getInputStream()) {
//      XmlFactoryConfiguration configuration = new XmlFactoryConfiguration();
//      configuration.read(is);
//      toolManager.configure(configuration);
//    }
//
//    var velocityEngine = new VelocityEngine(
//      new Properties() {{
//        put(RuntimeConstants.RESOURCE_LOADER, "classpath");
//        put("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogChute");
//        put("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
//        put("velocimacro.library", "templates/vm/l2joy.ru/body.vm,templates/vm/main.vm,finch-admin/vm/lib/utils.vm");
//      }}
//    );
//
//    var velocityViewRender = new VelocityViewRender(
//      velocityEngine,
//      toolManager,
//      null,
//      velocityProperties.toMap()
//    );
//
//    velocityViewRender.setPrefix("templates/vm/");
//
//    return velocityViewRender;
//  }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        String currentPath = new File(".").getAbsolutePath();
//        String location = "file:///" + currentPath + "/public/";
//        registry.addResourceHandler("/**").addResourceLocations(location);
//    }

  @Bean(name = "localeResolver")
  public LocaleResolver localeResolver() {
    CookieLocaleResolver resolver = new CookieLocaleResolver();
    resolver.setDefaultLocale(new Locale("ru"));
    resolver.setCookieName("lang");
    resolver.setCookieMaxAge(Integer.MAX_VALUE);
    return resolver;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    localeChangeInterceptor.setParamName("lang");
    registry.addInterceptor(localeChangeInterceptor);
    registry.addInterceptor(siteInterceptor);
  }
}
