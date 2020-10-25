package computerdatabase

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class ExampleSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://demo.nopcommerce.com/") 
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val scn = scenario("Example Scenario") 
    .exec(
      http("HomePage")
        .get("/")
    )
    .pause(5)
    .exec(
      http("SearchForPage")
        .get("/search?q=apple")
    )
    .pause(2)
    .exec(
      http("ClickTheProduct")
        .get("/apple-macbook-pro-13-inch")
    )
    .pause(3)
    .exec(
      http("ClickAddToCart")
        .get("//input[@id = 'add-to-cart-button-4']")
    )
    .pause(2)
    .exec(
      http("GoToCart")
        .get("/cart")
    )

  setUp(scn.inject(atOnceUsers(1)).protocols(httpProtocol))
}
