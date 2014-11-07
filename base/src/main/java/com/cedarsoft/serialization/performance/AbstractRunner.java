package com.cedarsoft.serialization.performance;


import org.apache.commons.lang3.time.StopWatch;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Johannes Schneider (<a href="mailto:js@cedarsoft.com">js@cedarsoft.com</a>)
 */
public class AbstractRunner {

  public Result runBenchmark( @Nonnull Runnable runnable, final int count ) {
    //Warmup
    runnable.run();
    runnable.run();
    runnable.run();

    List<Long> times = new ArrayList<Long>();

    for ( int i = 0; i < count; i++ ) {
      StopWatch stopWatch = new StopWatch();
      stopWatch.start();
      runnable.run();
      stopWatch.stop();

      times.add( stopWatch.getTime() );
    }

    Result result = new Result( times );

    System.out.println( "-----------------------" );
    System.out.println( result );
    System.out.println( "-----------------------" );

    return result;
  }

}
