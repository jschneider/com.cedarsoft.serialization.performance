package com.cedarsoft.serialization.performance;

import org.junit.*;

import static org.fest.assertions.Assertions.assertThat;


public class AbstractRunnerTest {
  @Test( timeout = 6000 )
  public void testIt() throws Exception {
    AbstractRunner runner = new AbstractRunner();
    Result result = runner.runBenchmark( () -> {
      try {
        Thread.sleep( 100 );
      } catch ( InterruptedException e ) {
        throw new RuntimeException( e );
      }
      return null;
    }, 10 );

    assertThat( result.getTimes() ).hasSize( 5 );
    for ( Long time : result.getTimes() ) {
      assertThat( time ).isGreaterThan( 900 ).isLessThan( 1100 );
    }
  }
}