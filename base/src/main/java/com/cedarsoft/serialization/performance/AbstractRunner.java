package com.cedarsoft.serialization.performance;


import org.apache.commons.lang3.time.StopWatch;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author Johannes Schneider (<a href="mailto:js@cedarsoft.com">js@cedarsoft.com</a>)
 */
public class AbstractRunner {

  public Result runBenchmark( @Nonnull Callable<?> runnable, final int count ) throws Exception {
    //Warmup
    run( runnable, count );
    run( runnable, count );
    run( runnable, count );

    List<Long> times = new ArrayList<Long>();

    for ( int run = 0; run < 5; run++ ) {
      StopWatch stopWatch = new StopWatch();
      stopWatch.start();
      run( runnable, count );
      stopWatch.stop();
      times.add( stopWatch.getTime() );
    }

    Result result = new Result( times, count );

    System.out.println( "-----------------------" );
    System.out.println( result );
    System.out.println( "-----------------------" );

    return result;
  }

  private void run( @Nonnull Callable<?> runnable, int count ) throws Exception {
    for ( int i = 0; i < count; i++ ) {
      runnable.call();
    }
  }

}
