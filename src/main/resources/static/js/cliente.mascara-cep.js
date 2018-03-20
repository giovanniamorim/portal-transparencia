var Portal = Portal || {};

Portal.MascaraCep = (function(){

	function MascaraCep(){
		this.inputCep = $('#cep');
	}
	
	MascaraCep.prototype.iniciar = function(){
		this.inputCep.mask('00.000-000');
	}
	
	
	return MascaraCep;
	
}());

$(function(){
	
	var mascaraCep = new Portal.MascaraCep();
	mascaraCep.iniciar();
	
});
