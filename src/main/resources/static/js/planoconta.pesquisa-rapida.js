Portal = Portal || {};

Portal.PesquisaRapidaPlanoConta = (function() {
	
	function PesquisaRapidaPlanoConta() {
		this.pesquisaRapidaPlanoContasModal = $('#pesquisaRapidaPlanoContas');
		this.nomeInput = $('#nomePlanoContaModal');
		this.pesquisaRapidaBtn = $('.js-pesquisa-rapida-planocontas-btn'); 
		this.containerTabelaPesquisa = $('#containerTabelaPesquisaRapidaPlanoContas');
		this.htmlTabelaPesquisa = $('#tabela-pesquisa-rapida-planoconta').html();
		this.template = Handlebars.compile(this.htmlTabelaPesquisa);
		this.mensagemErro = $('.js-mensagem-erro');
	}
	
	PesquisaRapidaPlanoConta.prototype.iniciar = function() {
		this.pesquisaRapidaBtn.on('click', onPesquisaRapidaClicado.bind(this));
		this.pesquisaRapidaPlanoContasModal.on('shown.bs.modal', onModalShow.bind(this));

	}
	
	function onModalShow() {
		this.nomeInput.focus();
	}
	
	function onPesquisaRapidaClicado(event) {
		event.preventDefault();
		
		$.ajax({
			url: this.pesquisaRapidaPlanoContasModal.find('form').attr('action'),
			method: 'GET',
			contentType: 'application/json',
			data: {
				nome: this.nomeInput.val()
			}, 
			success: onPesquisaConcluida.bind(this),
			error: onErroPesquisa.bind(this)
		});
	}
	
	function onPesquisaConcluida(resultado) {
		this.mensagemErro.addClass('hidden');
		
		var html = this.template(resultado);
		this.containerTabelaPesquisa.html(html);
		
		var tabelaPlanoContaPesquisaRapida = new Portal.TabelaPlanoContaPesquisaRapida(this.pesquisaRapidaPlanoContasModal);
		tabelaPlanoContaPesquisaRapida.iniciar();
	} 
	
	function onErroPesquisa() {
		this.mensagemErro.removeClass('hidden');
	}
	
	return PesquisaRapidaPlanoConta;
	
}());

Portal.TabelaPlanoContaPesquisaRapida = (function() {
	
	function TabelaPlanoContaPesquisaRapida(modal) {
		this.modalPlanoConta = modal;
		this.planoconta = $('.js-planoconta-pesquisa-rapida');
	}
	
	TabelaPlanoContaPesquisaRapida.prototype.iniciar = function() {
		this.planoconta.on('click', onPlanoContaSelecionado.bind(this));
	}
	
	function onPlanoContaSelecionado(evento) {
		this.modalPlanoConta.modal('hide');
		
		var planocontaSelecionado = $(evento.currentTarget);
		$('#nomePlanoConta').val(planocontaSelecionado.data('nome'));
		$('#codigoPlanoConta').val(planocontaSelecionado.data('codigo'));
	}
	
	return TabelaPlanoContaPesquisaRapida;
	
}());

$(function() {
	var pesquisaRapidaPlanoConta = new Portal.PesquisaRapidaPlanoConta();
	pesquisaRapidaPlanoConta.iniciar();
});
