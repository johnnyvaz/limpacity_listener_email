package br.com.limpacity.listenerEmail.service.impl;

import br.com.limpacity.producer.dto.NotificaEmailDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Session;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Slf4j
@Service
public class ColetaServiceImpl {

    private SendMail sendMail;

    @RabbitListener(queues="${spring.rabbitmq.events.solicitaColeta.queueNotificarEmail}")
    public void processColeta(Message message) throws UnsupportedEncodingException, JsonProcessingException, MessagingException {

        String json = new String(message.getBody(), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();

        NotificaEmailDTO notificaEmail = mapper.readValue(json, NotificaEmailDTO.class);

        // sendMail.enviarEmail(notificaEmail);

        System.out.println("Enviando email : " + notificaEmail);

    }

}
