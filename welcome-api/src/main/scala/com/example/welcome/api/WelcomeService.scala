package com.example.welcome.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}

trait WelcomeService extends Service {

  def welcome(name: String): ServiceCall[NotUsed, String]

  override def descriptor = {
    import Service._

    named("welcome").
      withCalls(
        pathCall("/api/welcome/:name", welcome _)
      ).withAutoAcl(true)
  }

}
