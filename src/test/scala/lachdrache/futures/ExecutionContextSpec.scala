package lachdrache.futures

import org.specs2.mutable.Specification
import scala.concurrent._
import scala.concurrent.duration.Duration


/**
 * You should run the test on its own, i.e.
 * `test-only lachdrache.futures.ExecutionContextSpec`
 *
 * Based on blog post and Gist by Jessica Kerr
 * - http://blog.jessitron.com/2014/02/scala-global-executioncontext-makes.html
 * - https://gist.github.com/jessitron/8777503
 */
class ExecutionContextSpec extends Specification {

  "scala.concurrent.blocking" should {
    val des = scala.concurrent.ExecutionContext.global

    def ct: String = Thread.currentThread.getName

    val n = Runtime.getRuntime.availableProcessors

    def hogThread(sec:Int):Future[String] = future {
      println("Sleeping on thread " + ct)
      Thread.sleep(sec * 1000)
      println("Freeing thread " + ct)
      ct
    } (des)

    def pauseThread(sec:Int):Future[String] = future {
      println("Sleeping on thread " + ct)
      blocking {Thread.sleep(sec * 1000) }
      println("Freeing thread " + ct)
      ct
    } (des)

    def runTestWith(f: Int => Future[String]): Int = {
      val futures = 1 to (n + 2) map { _ => f(1) }
      val allThreads = Future.fold(futures)(Set[String]())(_ + _)(des) // set of thread names
      val numberOfThreads = allThreads.map(_.size)(des)
      Await.result(numberOfThreads, Duration.Inf)
    }

    def usingHog: Int = runTestWith(hogThread)
    def usingPause: Int = runTestWith(pauseThread)

    "additional threads are created when using scala.concurrent.blocking" in {
      n === usingHog
      n+2 === usingPause
    }
  }

}