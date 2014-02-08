package lachdrache.futures

import org.specs2.mutable.Specification
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Failure

class FuturesWithForComprehensionsSpec extends Specification {

  case class TstException() extends Exception

  private def tst: Future[String] =
    for {
      a <- Future("a")
      intermediateAssignment = {
        throw TstException()
      }
      b <- Future(a + "b")
    } yield b

  "exception of assigment in for comprehension" should {
    "end up in Try of the future" in {
      val future = tst
      Await.ready(future, Duration.Inf)
      future.value.get === Failure(TstException())
    }
  }
}
