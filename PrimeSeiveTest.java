/*
 * This Java source file was generated by the Gradle 'init' task.
 */
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PrimeSieveTest {
  @Test public void testSomeLibraryMethod() {
    PrimeSieve primeSieve = new PrimeSieve();
    assertThat(primeSieve.numPrimes3(200), is(46));
  }
}
