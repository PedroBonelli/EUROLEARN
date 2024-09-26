package com.eurolearn.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioTreinamentoDTO {
	private long cpf;
	private int TreinamentoId;
	private List<Long> cpfs;
}
