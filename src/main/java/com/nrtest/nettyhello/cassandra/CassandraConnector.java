package com.nrtest.nettyhello.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

import java.util.function.Consumer;

public class CassandraConnector {

    public static void executeCassandra(Consumer<Session> sessionConsumer) {
        try (Cluster cluster = connect()) {
            Session session = cluster.connect();
            sessionConsumer.accept(session);
        }
    }

    private static Cluster connect() {
        String host = "127.0.0.1";
        return Cluster.builder()
                .addContactPoint(host)
                .build();
    }
}