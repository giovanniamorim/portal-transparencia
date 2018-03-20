package com.sindifisco.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sindifisco.portal.model.TipoDocumento;

@Repository
public interface TiposDocumentos extends JpaRepository<TipoDocumento, Long>{

}
