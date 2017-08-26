package com.example.welcome.impl

import akka.NotUsed
import com.example.welcome.api.WelcomeService
import com.lightbend.lagom.scaladsl.api.ServiceCall
import scala.concurrent.Future

class WelcomeServiceImpl extends WelcomeService {

  override def welcome(name: String): ServiceCall[NotUsed, String] = {
    ServiceCall { _ =>
      Future.successful("Welcome, " + name)
    }
  }

}
