/*
 * This Scala Testsuite was auto generated by running 'gradle init --type scala-library'
 * by 'shany' at '12/10/16 1:03 AM' with Gradle 3.2.1
 *
 * @author shany, @date 12/10/16 1:03 AM
 */

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class LibrarySuite extends FunSuite {
  test("someLibraryMethod is always true") {
    def library = new Library()
    assert(library.someLibraryMethod)
  }
}
