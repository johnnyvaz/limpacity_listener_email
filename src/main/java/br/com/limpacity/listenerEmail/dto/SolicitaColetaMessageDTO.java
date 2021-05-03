package br.com.limpacity.listenerEmail.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class SolicitaColetaMessageDTO extends MessageDTO {

	private SolicitaBodyDTO loadRequest;

}
