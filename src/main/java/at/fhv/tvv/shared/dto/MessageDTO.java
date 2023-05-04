package at.fhv.tvv.shared.dto;

import java.io.Serializable;
import java.util.Objects;

public class MessageDTO implements Serializable {
    private String topicName;
    private String title;
    private String content;

    public MessageDTO() {

    }

    public MessageDTO(String topicName, String title, String content) {
        this.topicName = topicName;
        this.title = title;
        this.content = content;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getTitle() {
        return title;
    }


    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageDTO that = (MessageDTO) o;
        return Objects.equals(topicName, that.topicName) && Objects.equals(title, that.title) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topicName, title, content);
    }
}

