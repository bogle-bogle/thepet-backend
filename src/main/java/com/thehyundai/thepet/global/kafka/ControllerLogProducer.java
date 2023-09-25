package com.thehyundai.thepet.global.kafka;

import com.thehyundai.thepet.global.timetrace.AopControllerVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ControllerLogProducer {
    private static final String CONTROLLER_LOG_TOPIC = "controller_execution_time_log";
    private final KafkaTemplate<String, AopControllerVO> kafkaTemplate;

    @Autowired
    public ControllerLogProducer(@Qualifier("controllerLogKafkaTemplate") KafkaTemplate<String, AopControllerVO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(AopControllerVO message) {
        this.kafkaTemplate.send(CONTROLLER_LOG_TOPIC, message);
    }
}