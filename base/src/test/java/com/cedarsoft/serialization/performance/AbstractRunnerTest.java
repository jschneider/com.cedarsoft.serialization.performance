package com.cedarsoft.serialization.performance;

import org.junit.*;

import static org.fest.assertions.Assertions.assertThat;


public class AbstractRunnerTest {
  @Test
  public void testIt() throws Exception {
    AbstractRunner runner = new AbstractRunner();
    Result result = runner.runBenchmark( () -> {
      try {
        Thread.sleep( 100 );
      } catch ( InterruptedException e ) {
        throw new RuntimeException( e );
      }
    }, 10 );

    assertThat( result.getTimes() ).hasSize( 10 );
  }
}