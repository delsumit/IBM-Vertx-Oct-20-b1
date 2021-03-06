package com.ibm.vertx.core.http;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.example.util.Runner;


public class SendJSON extends AbstractVerticle {
  public static void main(String[] args) {
    Runner.runExample(SendJSON.class);
  }

  @Override
  public void start() throws Exception {
    super.start();
    HttpServer server = vertx.createHttpServer();

    //handle client request async
    server.requestHandler(request -> {
      //send a reponse
      vertx.setTimer(5000, handler -> {
        JsonObject message = new JsonObject().put("message", "Hello");
        request
          .response()
          .putHeader("content-type", "application/json")
          .setStatusCode(200)
          .end(message.encodePrettily());
      });

    });


    //start server
    server.listen(3000, httpServerAsyncResult -> {
      if (httpServerAsyncResult.succeeded()) {
        System.out.println("Server is Ready at " + httpServerAsyncResult.result().actualPort());
      } else {
        System.out.println("Server failed " + httpServerAsyncResult.cause());
      }
    });


  }
}
