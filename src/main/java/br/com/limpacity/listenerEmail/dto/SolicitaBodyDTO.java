package br.com.limpacity.listenerEmail.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public abstract class SolicitaBodyDTO {
	
	private String action;	
	
}
