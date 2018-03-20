package com.sindifisco.portal.thymeleaf;


import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.sindifisco.portal.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.sindifisco.portal.thymeleaf.processor.MenuAttributeTagProcessor;
import com.sindifisco.portal.thymeleaf.processor.MessageElementTagProcessor;
import com.sindifisco.portal.thymeleaf.processor.OrderElementTagProcessor;
import com.sindifisco.portal.thymeleaf.processor.PaginationElementTagProcessor;

public class PortalDialect extends AbstractProcessorDialect{

	public PortalDialect() {
		super("Portal Sindifisco", "portal", StandardDialect.PROCESSOR_PRECEDENCE);
		
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		processadores.add(new MenuAttributeTagProcessor(dialectPrefix));
		return processadores;
	}

}