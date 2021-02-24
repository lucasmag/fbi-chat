package org.fbichat.entrys;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import org.fbichat.Space;
import org.fbichat.service.Service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpaceClass
public class Spy implements Serializable {
    private String word;

    public Spy() {}

    public Spy(String word) {
        this.word = word;
    }

    @SpaceId(autoGenerate=false)
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public static void scan(Message message) {
        String msg = message.getContent();
        Spy[] wordsToCheck = Space.get().readMultiple(new Spy(null));

        List<String> suspectWords = Arrays
                .stream(wordsToCheck)
                .map(spy -> spy.word)
                .filter(msg::contains)
                .collect(Collectors.toList());

        if(!suspectWords.isEmpty())
            Service.analyze(message, suspectWords);
    }

    public static List<Spy> getAll() {
        return Arrays.asList(Space.get().readMultiple(new Spy(null)).clone());
    }

    @Override
    public String toString() {
        return this.word;
    }

    public void save() {
        Space.get().write(this);
    }
}
