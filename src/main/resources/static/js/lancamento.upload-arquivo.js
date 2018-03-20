var Portal = Portal || {};

Portal.UploadArquivo = (function() {
	
	function UploadArquivo() {
		this.inputNomeArquivo = $('input[name=arquivo]');
		this.inputContentType = $('input[name=contentType]');
		this.novoArquivo = $('input[name=novoArquivo]');
		
		this.htmlArquivoLancamentoTemplate = $('#arquivo-lancamento').html();
		this.template = Handlebars.compile(this.htmlArquivoLancamentoTemplate);
		
		this.containerArquivoLancamento = $('.js-container-arquivo-lancamento');
		
		this.uploadDrop = $('#upload-drop');
	}
	
	UploadArquivo.prototype.iniciar = function () {
		var settings = {
			type: 'json',
			filelimit: 1,
			allow: '*.(jpg|jpeg|png)',
			action: this.containerArquivoLancamento.data('url-arquivos'),
			complete: onUploadCompleto.bind(this),
			beforeSend: adicionarCsrfToken
		}
		
		UIkit.uploadSelect($('#upload-select'), settings);
		UIkit.uploadDrop(this.uploadDrop, settings);
		
		if (this.inputNomeArquivo.val()) {
			renderizarArquivo.call(this, { nome:  this.inputNomeArquivo.val(), contentType: this.inputContentType.val()});
		}
	}
	
	function onUploadCompleto(resposta) {
		this.novoArquivo.val('true');
		renderizarArquivo.call(this, resposta);
	}
	
	function renderizarArquivo(resposta) {
		this.inputNomeArquivo.val(resposta.nome);
		this.inputContentType.val(resposta.contentType);
		
		this.uploadDrop.addClass('hidden');
		
		var arquivo = '';
		if (this.novoArquivo.val() == 'true') {
			arquivo = 'temp/';
		}
		arquivo += resposta.nome;
		
		var htmlArquivoLancamento = this.template({arquivo: arquivo});
		this.containerArquivoLancamento.append(htmlArquivoLancamento);
		
		$('.js-remove-arquivo').on('click', onRemoverArquivo.bind(this));
	}
	
	function onRemoverArquivo() {
		$('.js-arquivo-lancamento').remove();
		this.uploadDrop.removeClass('hidden');
		this.inputNomeArquivo.val('');
		this.inputContentType.val('');
		this.novoArquivo.val('false');
	}
	
	function adicionarCsrfToken(xhr) {
		var token = $('input[name=_csrf]').val();
		var header = $('input[name=_csrf_header]').val();
		xhr.setRequestHeader(header, token);
	}
	
	return UploadArquivo;
	
})();

$(function() {
	var uploadArquivo = new Portal.UploadArquivo();
	uploadArquivo.iniciar();
});