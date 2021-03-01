package org.fbichat.service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.fbichat.stub.None;
import org.fbichat.entrys.Message;
import org.fbichat.stub.Report;
import org.fbichat.stub.ServiceGrpc;

import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Service {
    public ManagedChannel channel;
    public static ServiceGrpc.ServiceBlockingStub stub;
    public Service(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext());
    }

    /** Construct client for accessing RouteGuide server using the existing channel. */
    public Service(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
        stub = ServiceGrpc.newBlockingStub(channel);
    }

    //TODO testiiig, refat later
    public static void analyze(Message message, List<String> suspectWords) {
        Report.Builder msgToAnalyze = Report.newBuilder();
        msgToAnalyze.setContent(message.getContent());
        msgToAnalyze.setDate(message.getDateTime().toInstant()  // Convert `java.util.Date` to `Instant`.
                .atOffset( ZoneOffset.of("-3") )  // Transform `Instant` to `OffsetDateTime`.
                .format( DateTimeFormatter.ISO_LOCAL_DATE_TIME ));
        msgToAnalyze.setFrom(message.getFrom().toString());
        msgToAnalyze.setTo(message.getTo().toString());
        msgToAnalyze.addAllSuspectWords(suspectWords);

        // the .analyze method is on server-side with Node.js :mindblowing:
        None none = stub.analyze(msgToAnalyze.build());
    }
}
