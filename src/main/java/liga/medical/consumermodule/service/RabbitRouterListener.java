package liga.medical.consumermodule.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.consumermodule.model.QueueNames;
import liga.medical.consumermodule.model.RabbitMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitRouterListener {

    private final ObjectMapper objectMapper;

    @RabbitListeners(value = {
            @RabbitListener(queues = QueueNames.ALERT_QUEUE),
            @RabbitListener(queues = QueueNames.DAILY_QUEUE),
            @RabbitListener(queues = QueueNames.ERROR_QUEUE)
            })
    public void receiveAlertDailyAndErrorMessages(String message) throws JsonProcessingException {
        RabbitMessageDto rabbitMessageDto = objectMapper.readValue(message, RabbitMessageDto.class);
        log.info("Сообщение из очереди {} прочитано", rabbitMessageDto.getType().toString());
    }
}
