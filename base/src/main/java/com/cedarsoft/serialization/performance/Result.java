package com.cedarsoft.serialization.performance;

import com.google.common.collect.ImmutableList;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author Johannes Schneider (<a href="mailto:js@cedarsoft.com">js@cedarsoft.com</a>)
 */
public class Result {
  @Nonnull
  private final List<Long> times;

  public Result( @Nonnull List<Long> times ) {
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

    for ( Long time : times ) {
      builder.append( time ).append( " ms\n" );
    }

    return builder.toString();
  }
}
