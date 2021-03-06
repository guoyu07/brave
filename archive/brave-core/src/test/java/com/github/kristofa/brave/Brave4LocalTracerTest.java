package com.github.kristofa.brave;

import brave.Tracing;
import org.junit.After;

public class Brave4LocalTracerTest extends LocalTracerTest {
  @Override Brave newBrave() {
    return TracerAdapter.newBrave(Tracing.newBuilder()
        .clock(clock::currentTimeMicroseconds)
        .localEndpoint(ZIPKIN_ENDPOINT)
        .reporter(spans::add).build().tracer());
  }

  @Override Brave newBrave(ServerClientAndLocalSpanState state) {
    return TracerAdapter.newBrave(Tracing.newBuilder()
        .clock(clock::currentTimeMicroseconds)
        .localEndpoint(ZIPKIN_ENDPOINT)
        .reporter(spans::add).build().tracer(), state);
  }

  @After public void close(){
    Tracing.current().close();
  }
}
