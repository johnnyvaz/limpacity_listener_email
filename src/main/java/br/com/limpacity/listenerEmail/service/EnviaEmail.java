package br.com.limpacity.listenerEmail.service;

import br.com.limpacity.producer.dto.NotificaEmailDTO;

public interface EnviaEmail {

    void enviarEmail(NotificaEmailDTO email);

}
