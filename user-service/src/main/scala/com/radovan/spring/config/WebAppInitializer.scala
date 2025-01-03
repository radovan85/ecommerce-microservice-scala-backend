package com.radovan.spring.config

import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

import jakarta.servlet.{ServletContext, ServletRegistration}

class WebAppInitializer extends WebApplicationInitializer {

  override def onStartup(servletContext: ServletContext): Unit = {
    val webContext = new AnnotationConfigWebApplicationContext()
    webContext.register(classOf[SpringMvcConfiguration])

    val initializer: ServletRegistration.Dynamic =
      servletContext.addServlet("Spring Initializer", new DispatcherServlet(webContext))

    initializer.setLoadOnStartup(1)
    initializer.addMapping("/")
  }

}

