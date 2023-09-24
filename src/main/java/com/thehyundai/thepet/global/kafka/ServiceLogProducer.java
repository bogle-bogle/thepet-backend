package com.thehyundai.thepet.global.kafka;

import com.thehyundai.thepet.global.timetrace.AopServiceVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ServiceLogProducer {
    private static final String TOPIC = "service_execution_time_log";
    private final KafkaTemplate<String, AopServiceVO> kafkaTemplate;

    @Autowired
    public ServiceLogProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(AopServiceVO message) {
        this.kafkaTemplate.send(TOPIC, message);
    }
}