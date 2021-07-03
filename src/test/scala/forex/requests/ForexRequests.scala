package forex.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.util.Random

object ForexRequests {

  val baseCurrenciesFeeder = csv("data/baseCurrencies.csv").random
  val currencyPairsFeeder = csv("data/currencyPairs.csv").random
  val rateBookingReqsFeeder = csv("data/rateBookingReqs.csv").random

  def getRateByBaseCurrency = {
    feed(baseCurrenciesFeeder)
      .exec(http("Get Rates By Base Currency")
        .get("/rates/latest/${baseCurrency}")
        .check(status.is(200))
      )
  }

  def getRateByCurrencyPair = {
    feed(currencyPairsFeeder)
      .exec(http("Get Rate By Currency Pair")
        .get("/rates/latest/${baseCurrency}/${counterCurrency}")
        .check(status.is(200))
      )
  }

  def bookRate = {
    feed(rateBookingReqsFeeder)
      .exec(
        http("Book Rate")
        .post("/rates/book")
        .body(ElFileBody("data/rateBookingReq.json")).asJson
          .check(status.is(200))
          .check(jsonPath("$..bookingRef").saveAs("rateBookingRef"))
          .check(jsonPath("$..rate").saveAs("rate"))
      )
  }

  def submitForexTradeDeal = {
      exec(http("Submit Trade Deal")
        .post("/deals")
        .body(ElFileBody("data/forexTradeDealReq.json")).asJson
        .check(status.is(200))
      )
    }


}