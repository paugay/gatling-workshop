package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class BasicSimulation extends Simulation {

  val httpProtocol = http
    .baseURL("http://computer-database.gatling.io")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:42.0) Gecko/20100101 Firefox/42.0")

  val scn = scenario("BasicSimulation")
		// Search
		.exec(http("request_0")
			.get("/computers"))
		.pause(11)
		.exec(http("request_1")
			.get("/computers?f=macbook"))
		.pause(9)
		.exec(http("request_2")
			.get("/computers/6"))
		.pause(11)

		// Browse
		.exec(http("request_3")
			.get("/"))
		.pause(13)
		.exec(http("request_4")
			.get("/computers?p=1"))
		.pause(1)
		.exec(http("request_5")
			.get("/computers?p=2"))
		.pause(547 milliseconds)
		.exec(http("request_6")
			.get("/computers?p=3"))
		.pause(529 milliseconds)
		.exec(http("request_7")
			.get("/computers?p=4"))
		.pause(402 milliseconds)
		.exec(http("request_8")
			.get("/computers?p=5"))
		.pause(392 milliseconds)
		.exec(http("request_9")
			.get("/computers?p=6"))
		.pause(7)

		// Edit
		.exec(http("request_10")
			.get("/computers/new"))
    .pause(33)
    .exec(http("request_11")
      .post("/computers")
      .formParam("name", "Pau's laptop")
      .formParam("introduced", "2015-11-23")
      .formParam("discontinued", "")
      .formParam("company", "1"))

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
