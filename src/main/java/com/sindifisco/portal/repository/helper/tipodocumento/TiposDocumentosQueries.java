package com.sindifisco.portal.repository.helper.tipodocumento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sindifisco.portal.model.TipoDocumento;
import com.sindifisco.portal.repository.filter.TipoDocumentoFilter;

public interface TiposDocumentosQueries {
	
	public Page<TipoDocumento> filtrar(TipoDocumentoFilter filtro, Pageable pageable);

}
