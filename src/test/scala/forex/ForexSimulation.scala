package forex

import forex.requests.ForexRequests

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class ForexSimulation extends Simulation {

  val API_HOST = "localhost"
  val API_PORT = "8080"

  val httpProtocol = http
    .baseUrl("http://" + getProperty("API_HOST", API_HOST) + ":" + getProperty("API_PORT", API_PORT))

  private def getProperty(propertyName: String, defaultValue: String) = {
    Option(System.getenv(propertyName))
      .orElse(Option(System.getProperty(propertyName)))
      .getOrElse(defaultValue)
  }

  object UserJourneys {
    def minPause = 1.second
    def maxPause = 3.second

    def browseForexRates = {
      repeat(5) {
        exec(ForexRequests.getRateByBaseCurrency)
          .pause(minPause, maxPause)
        .exec(ForexRequests.getRateByCurrencyPair)
          .pause(minPause, maxPause)
      }
    }

    def browseForexRatesAndBookRate = {
      repeat(5) {
        exec(ForexRequests.getRateByBaseCurrency)
          .pause(minPause, maxPause)
        .exec(ForexRequests.getRateByCurrencyPair)
          .pause(minPause, maxPause)
      }
      .exec(ForexRequests.bookRate)
        .pause(minPause, maxPause)
        .exec(ForexRequests.submitForexTradeDeal)
    }

    def browseForexRatesAndSubmitForexTradeDeal = {
      repeat(3) {
        exec(ForexRequests.getRateByCurrencyPair)
          .pause(minPause, maxPause)
      }
      .exec(ForexRequests.bookRate)
        .pause(minPause, maxPause)
        .exec(ForexRequests.submitForexTradeDeal)
    }
  }

  val scn = scenario("ForexSimulation")
    .randomSwitch (
      60d -> exec(UserJourneys.browseForexRates),
      30d -> exec(UserJourneys.browseForexRatesAndBookRate),
      10d -> exec(UserJourneys.browseForexRatesAndSubmitForexTradeDeal)
    )


  setUp(
    scn.inject(
      constantUsersPerSec(10) during (1.minute)
//        constantConcurrentUsers(10).during(10.seconds),
//        rampConcurrentUsers(10).to(20).during(10.seconds)

//      incrementConcurrentUsers(5) // Int
//        .times(5)
//        .eachLevelLasting(5.seconds)
//        .separatedByRampsLasting(5.seconds)
//        .startingFrom(10) // Int

    ).protocols(httpProtocol)
  )

}
