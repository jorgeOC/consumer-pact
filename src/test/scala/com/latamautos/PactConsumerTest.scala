package com.latamautos

import org.json4s.DefaultFormats
import org.scalatest.{FunSpec, Matchers}


/**
  * Created by jorgeortiz on 11/24/16.
  */
class PactConsumerTest extends FunSpec with Matchers {

  import com.itv.scalapact.ScalaPactForger._
  implicit val formats = DefaultFormats

  describe("Connecting to the Provider service") {
    it("should be able to get an auth token") {
      forgePact
        .between("Consumer")
        .and("Provider")
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
}