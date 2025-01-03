package com.radovan.spring.interceptors

import org.springframework.http.client.{ClientHttpRequestExecution, ClientHttpRequestInterceptor, ClientHttpResponse}
import org.springframework.http.HttpRequest
import org.springframework.stereotype.Component
import org.springframework.web.context.request.{RequestAttributes, RequestContextHolder}

@Component
class RestTemplateHeaderModifierInterceptor extends ClientHttpRequestInterceptor {

  override def intercept(
                          request: HttpRequest,
                          body: Array[Byte],
                          execution: ClientHttpRequestExecution
                        ): ClientHttpResponse = {

    val requestAttributes: RequestAttributes = RequestContextHolder.getRequestAttributes

    if (requestAttributes != null) {
      val authHeader = requestAttributes.getAttribute("Authorization", RequestAttributes.SCOPE_REQUEST).asInstanceOf[String]
      if (authHeader != null) {
        request.getHeaders.add("Authorization", authHeader)
      }
    }

    request.getHeaders.set("X-Source-Service", "customer-service")

    execution.execute(request, body)
  }
}
