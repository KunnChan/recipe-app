package com.pk.love.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.pk.love.command.UnitOfMeasureCommand;
import com.pk.love.domain.UnitOfMeasure;

import lombok.Synchronized;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand>{

	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasureCommand convert(UnitOfMeasure source) {
		if(source == null)return null;
		
		final UnitOfMeasureCommand command = new UnitOfMeasureCommand();
			command.setId(source.getId());
			command.setDescription(source.getDescription());
		return command;
	}

}
