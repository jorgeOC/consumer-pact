package com.latamautos

import org.json4s.DefaultFormats
import org.scalatest.{FunSpec, Matchers}


/**
  * Created by jorgeortiz on 11/24/16.
  */
class PactConsumerTest extends FunSpec with Matchers {

  import com.itv.scalapact.ScalaPactForger._
  implicit val formats = DefaultFormats
  val MICROSERVICE_NAME: String = "microservice-consumer1"
  val MICROSERVICE_PROVIDER1: String = "microservice-provider1"
  val MICROSERVICE_PROVIDER2: String = "microservice-provider2"

  describe("Connecting to the Provider1 service") {
    it("should be able to get a resource") {

      forgePact
        .between(MICROSERVICE_NAME)
        .and(MICROSERVICE_PROVIDER1)
        .addInteraction(
          interaction
            .description("Fetching least secure auth token ever")
            .uponReceiving(
              method = GET,
              path = "/auth_token",
              query = "namespace=ptx&appid=1",
              headers = Map("Accept" -> "application/json"),
              body = None,
              None
            )
            .willRespondWith(
            status = 200,
            headers = Map("Content-Type" -> "application/json; charset=UTF-8"),
            body = ""
          )
        )
        .runConsumerTest { mockConfig =>
          assert(true)
        }
    }
  }
  describe("Connecting to the Provider2 service") {
    it("should be able to get a resource") {

      forgePact
        .between(MICROSERVICE_NAME)
        .and(MICROSERVICE_PROVIDER2)
        .addInteraction(
          interaction
            .description("Fetching least secure auth token ever provider2")
            .uponReceiving(
              method = GET,
              path = "/auth_token",
              query = "namespace=ptx&appid=1",
              headers = Map("Accept" -> "application/json"),
              body = None,
              None
            )
            .willRespondWith(
              status = 200,
              headers = Map("Content-Type" -> "application/json; charset=UTF-8"),
              body = ""
            )
        )
        .runConsumerTest { mockConfig =>
          assert(true)
        }
    }
  }
}
