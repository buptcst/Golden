import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MovieSuite extends FunSuite{
 test("someLibraryMethod is always true") {
    def movie = new Movie()
    assert(movie.watchMovie)
  }
}