package allocation.mechanism

/**
  * Created by asteroid-m on 1/5/17.
  */
import org.slf4j.LoggerFactory

trait LoggingHelper {
  val logger = LoggerFactory.getLogger(this.getClass().getName())
}