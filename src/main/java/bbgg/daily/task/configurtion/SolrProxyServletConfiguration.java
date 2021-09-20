package bbgg.daily.task.configurtion;

import org.mitre.dsmiley.httpproxy.ProxyServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolrProxyServletConfiguration {

  @Bean
  public ServletRegistrationBean servletRegistrationBean1(){
    ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new ProxyServlet(), "/proxy/*");
    servletRegistrationBean.addInitParameter("targetUri", "https://www.jetbrains.com");
    servletRegistrationBean.addInitParameter(ProxyServlet.P_LOG, "false");
    return servletRegistrationBean;
  }
}