package com.bunq.chatApp.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BaseController {

    final ModelMapper mapper;

    public BaseController(ModelMapper mapper) {
        this.mapper = mapper;
        this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public <S, D> D map(S sourceInstance, Class<D> destinationTypeClass) {
        if (sourceInstance == null) return null;
        setMapperAmbiguity();
        return mapper.map(sourceInstance, destinationTypeClass);
    }

    public <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
        if (CollectionUtils.isEmpty(entityList)) return null;
        setMapperAmbiguity();
        return entityList.stream().map(entity -> map(entity, outCLass)).collect(Collectors.toList());
    }

    private void setMapperAmbiguity() {
        if (!ObjectUtils.isEmpty(mapper) && !ObjectUtils.isEmpty(mapper.getConfiguration())) {
            mapper.getConfiguration().setAmbiguityIgnored(true);
        }
    }

}
