package com.cedarsoft.serialization.performance.small;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.Nonnull;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Johannes Schneider (<a href="mailto:js@cedarsoft.com">js@cedarsoft.com</a>)
 */
public class BookSerializerRunner extends AbstractBookSerializeRunner {
  public BookSerializerRunner() {
    super( ScenarioSize.SMALL, new Deserializer() {
      private final ObjectMapper mapper = new ObjectMapper();
      @Nonnull
      @Override
      public Book deserializeBook( @Nonnull byte[] serialized ) throws IOException {
        return mapper.readValue( serialized, Book.class );
      }
    } );
  }

  @Override
  protected byte[] serializeBook( @Nonnull Book book ) throws IOException {
    ObjectMapper mapper = new ObjectMapper();

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    mapper.writeValue( out, book );

    return out.toByteArray();
  }

  public static void main( String[] args ) throws Exception {
    new BookSerializerRunner().runDeserializationBenchmark();
  }
}
