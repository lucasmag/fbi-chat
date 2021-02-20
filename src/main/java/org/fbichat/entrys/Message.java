package org.fbichat.entrys;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import javafx.scene.control.Label;
import org.fbichat.Space;
import org.apache.commons.lang3.ArrayUtils;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@SpaceClass
public class Message implements Comparable<Message>{
    private String content;
    private User from;
    private User to;
    private String id;
    private Date dateTime; //Hora da mensagem para ordenaçao no chat


    public Message() {}

    public Message(User from, User to, String content, Date date) {
        this.content = content;
        this.from = from;
        this.to = to;
        this.dateTime = date;
    }

    @SpaceId(autoGenerate = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date datetime) {
        this.dateTime = datetime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    @Override
    public int compareTo(Message o) {
        return getDateTime().compareTo(o.getDateTime());
    }

    public void send() {
        Space.get().write(this);
    }

    public Label format() {
        String msg = String.format("%s: %s", this.getFrom(), this.getContent());
        return new Label(msg);
    }

    public static List<Label> getAll(User from, User to) {
        Message[] fromMsgs = Space.get().readMultiple(new Message(from, to, null, null));
        Message[] toMsgs = Space.get().readMultiple(new Message(to, from, null, null));

        Message[] allMsgs = ArrayUtils.addAll(fromMsgs, toMsgs);

        return Arrays.stream(allMsgs)
                .sorted()
                .map(m -> m.format())
                .collect(Collectors.toList());
    }

}
