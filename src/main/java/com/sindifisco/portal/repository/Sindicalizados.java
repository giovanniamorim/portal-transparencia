package com.sindifisco.portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sindifisco.portal.dto.SindicalizadoDTO;
import com.sindifisco.portal.model.Sindicalizado;
import com.sindifisco.portal.model.TipoSindicalizado;
import com.sindifisco.portal.repository.helper.sindicalizado.SindicalizadosQueries;

@Repository
public interface Sindicalizados extends JpaRepository<Sindicalizado, Long>, SindicalizadosQueries{
	
	public Optional<Sindicalizado> findByCpf(String cpf);

	public List<Sindicalizado> findByNomeStartingWithIgnoreCase(String nome);

	public List<SindicalizadoDTO> findByTipoSindicalizado(TipoSindicalizado tipoSindicalizado);

}
