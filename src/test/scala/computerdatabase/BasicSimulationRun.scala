package computerdatabase

import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder

object BasicSimulationRun extends App {

  val props = new GatlingPropertiesBuilder
  props.simulationClass(classOf[BasicSimulation].getName)
  Gatling.fromMap(props.build)
}
