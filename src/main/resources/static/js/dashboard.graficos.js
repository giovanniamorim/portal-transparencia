var Portal = Portal || {};

Portal.GraficoLancamentoReceitaPorMes = (function() {
	
	function GraficoLancamentoReceitaPorMes() {
		this.ctx = $('#graficoLancamentoReceitaPorMes')[0].getContext('2d');
	}
	
	GraficoLancamentoReceitaPorMes.prototype.iniciar = function() {
		$.ajax({
			url: 'lancamentos/receitaTotalPorMes',
			method: 'GET', 
			success: onDadosRecebidos.bind(this)
		});
	}
	
	function onDadosRecebidos(lancamentoMes) {
		var meses = [];
		var valores = [];
		lancamentoMes.forEach(function(obj) {
			meses.unshift(obj.mes);
			valores.unshift(obj.total);
		});
		
		var graficoLancamentosPorMes = new Chart(this.ctx, {
		    type: 'line',
		    data: {
		    	labels: meses,
		    	datasets: [{
		    		label: 'Receitas por mês',
		    		backgroundColor: "rgba(26,179,148,0.5)",
	                pointBorderColor: "rgba(26,179,148,1)",
	                pointBackgroundColor: "#fff",
	                data: valores
		    	}]
		    },
		});
	}
	
	return GraficoLancamentoReceitaPorMes;
}());
	


$(function() {
	var graficoLancamentoReceitaPorMes = new Portal.GraficoLancamentoReceitaPorMes();
	graficoLancamentoReceitaPorMes.iniciar();
	
});