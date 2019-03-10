package com.cursomc.domain.enums;

public enum EstadoPagamento {
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private Integer cod;
	private String descricao;
	
	private EstadoPagamento(Integer cod, String desc) {
		this.cod = cod;
		descricao = desc;
	}
	
	public Integer getCod() {
		return cod;
	}
	
	public String getDesc() {
		return descricao;
	}
	
	public static EstadoPagamento toEnum(Integer cod) {
		if (cod == null)
			return null;
		
		for (EstadoPagamento x : EstadoPagamento.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("ID inválido: " + cod);
	}
}
