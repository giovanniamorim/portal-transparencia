package com.sindifisco.portal.dto;

import java.math.BigDecimal;

public class LancamentoReceita {
	
		private String mes;
		private BigDecimal totalReceitas;
		private LancamentoReceita totalDespesas;
		
		public LancamentoReceita(String mes, BigDecimal totalReceitas, LancamentoReceita totalDespesas) {
			this.mes = mes;
			this.totalReceitas = totalReceitas;
			this.totalDespesas = totalDespesas;
		}

		public String getMes() {
			return mes;
		}

		public void setMes(String mes) {
			this.mes = mes;
		}

		public BigDecimal getTotalReceitas() {
			return totalReceitas;
		}

		public void setTotalReceitas(BigDecimal totalReceitas) {
			this.totalReceitas = totalReceitas;
		}

		public LancamentoReceita getTotalDespesas() {
			return totalDespesas;
		}

		public void setTotalDespesas(LancamentoReceita totalDespesas) {
			this.totalDespesas = totalDespesas;
		}
		
		
		

	}

	
	
	

