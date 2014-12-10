package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {
	
	private static final double ACRESCIMO_5_PORCENTO = 0.05;
	private static final double ACRESCIMO_10_PORCENTO = 0.10;
	private static final double ACRESCIMO_20_PORCENTO = 0.20;
	private static final double ACRESCIMO_50_PORCENTO = 0.50;
	private static final int DURACAO_MIN = 60;
	
	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		
		if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.CINEMA) || sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.SHOW)) {
			preco = tratamentoCinemaShow(sessao, ACRESCIMO_5_PORCENTO, ACRESCIMO_10_PORCENTO);
		} else if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.BALLET)) {
			preco = tratamentoBalletOrquestra(sessao, ACRESCIMO_50_PORCENTO, ACRESCIMO_20_PORCENTO);
		} else if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.ORQUESTRA)) {
			preco = tratamentoBalletOrquestra(sessao, ACRESCIMO_50_PORCENTO, ACRESCIMO_20_PORCENTO);
		}  else {
			preco = sessao.getPreco();
		} 

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	
	private static BigDecimal tratamentoCinemaShow(Sessao sessao, double acrescimoIngresso, double acrescimoPreco) {
		BigDecimal preco;
		preco = calculaAcrescimo(sessao, acrescimoIngresso,
				acrescimoPreco);
		return preco;
	}

	private static BigDecimal tratamentoBalletOrquestra(Sessao sessao, double acrescimoIngresso, double acrescimoPreco) {
		BigDecimal preco;
		preco = calculaAcrescimo(sessao, acrescimoIngresso,
				acrescimoPreco);
		
		if(sessao.getDuracaoEmMinutos() > DURACAO_MIN){
			preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(ACRESCIMO_10_PORCENTO)));
		}
		return preco;
	}

	private static BigDecimal calculaAcrescimo(Sessao sessao, double acrescimoIngresso, double acrescimoPreco) {
		BigDecimal preco;
		
		if((sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue() <= acrescimoIngresso) { 
			preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(acrescimoPreco)));
		} else {
			preco = sessao.getPreco();
		}
		return preco;
	}
}