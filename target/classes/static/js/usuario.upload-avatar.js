var Portal = Portal || {};

Portal.UploadAvatar = (function(){
	
	function UploadAvatar(){
		this.inputNomeAvatar = $('input[name=avatar]');
		this.inputContentType = $('input[name=contentType]')
		
		this.htmlAvatarUsuarioTemplate = $('#avatar-usuario').html();
		this.template = Handlebars.compile(this.htmlAvatarUsuarioTemplate);
		
		this.containerAvatarUsuario = $('.js-container-avatar-usuario');

		this.uploadDrop = $('#upload-drop');
		
	}
	
	UploadAvatar.prototype.iniciar = function(){
		var settings = {
				type: 'json',
				filelimit: 1,
				allow: '*.(jpg|jpeg|png)',
				action: this.containerAvatarUsuario.data('url-avatars'),
				complete: onUploadCompleto.bind(this),
				beforeSend: adicionarCsrfToken
		}
		UIkit.uploadSelect($('#upload-select'), settings);
		UIkit.uploadDrop(this.uploadDrop, settings);
		
		if(this.inputNomeAvatar.val()){
			onUploadCompleto.call(this, {nome: this.inputNomeAvatar.val(), contentType: this.inputContentType.val()});
		}
	}
	
	function onUploadCompleto(resposta){
		this.inputNomeAvatar.val(resposta.nome);
		this.inputContentType.val(resposta.contentType);
		
		this.uploadDrop.addClass('hidden');
		var htmlAvatarUsuario = this.template({nomeAvatar: resposta.nome});
		this.containerAvatarUsuario.append(htmlAvatarUsuario);
		
		$('.js-remove-avatar').on('click', onRemoveAvatar.bind(this));
		
	}
	
	function onRemoveAvatar(){
		$('.js-avatar-usuario').remove();
		this.uploadDrop.removeClass('hidden');
		this.inputNomeAvatar.val('');
		this.inputContentType.val('');
	}
	
	function adicionarCsrfToken(xhr){
		var token = $('input[name=_csrf]').val();
		var header = $('input[name=_csrf_header]').val();
		xhr.setRequestHeader(header, token);
	}
	
	return UploadAvatar;
	
})();

$(function(){
	var uploadAvatar = new Portal.UploadAvatar();
	uploadAvatar.iniciar();
});