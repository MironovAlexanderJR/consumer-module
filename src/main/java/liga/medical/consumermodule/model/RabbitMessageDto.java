package liga.medical.consumermodule.model;

import lombok.Data;

@Data
public class RabbitMessageDto {

    private MessageType type;

    private String content;
}
