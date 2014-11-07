package com.cedarsoft.serialization.performance;

import com.google.common.collect.ImmutableList;

import javax.annotation.Nonnull;
import java.text.NumberFormat;
import java.util.List;

/**
 * @author Johannes Schneider (<a href="mailto:js@cedarsoft.com">js@cedarsoft.com</a>)
 */
public class Result {
  @Nonnull
  private final List<Long> times;
  private final int count;

  public Result( @Nonnull List<Long> times, int count ) {
    this.count = count;
    this.times = ImmutableList.copyOf( times );
  }

  @Nonnull
  public List<Long> getTimes() {
    //noinspection ReturnOfCollectionOrArrayField
    return times;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    NumberFormat numberInstance = NumberFormat.getNumberInstance();
    for ( Long time : times ) {
      builder.append( time ).append( " ms\t\t for " ).append( numberInstance.format( count ) ).append( "\t\t" ).append( numberInstance.format( 1000.0 * count / time ) ).append( " actions per second" ).append( "\n" );
    }

    return builder.toString();
  }
}
