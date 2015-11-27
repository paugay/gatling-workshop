package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._

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
		.exec(http("request_1")
			.get("/computers?f=macbook"))
		.exec(http("request_2")
			.get("/computers/6"))

		// Browse
		.exec(http("request_3")
			.get("/"))
		.exec(http("request_4")
			.get("/computers?p=1"))
		.exec(http("request_5")
			.get("/computers?p=2"))
		.exec(http("request_6")
			.get("/computers?p=3"))
		.exec(http("request_7")
			.get("/computers?p=4"))
		.exec(http("request_8")
			.get("/computers?p=5"))
		.exec(http("request_9")
			.get("/computers?p=6"))

		// Edit
		.exec(http("request_10")
			.get("/computers/new"))
    .exec(http("request_11")
      .post("/computers")
      .formParam("name", "Pau's laptop")
      .formParam("introduced", "2015-11-23")
      .formParam("discontinued", "")
      .formParam("company", "1"))

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
