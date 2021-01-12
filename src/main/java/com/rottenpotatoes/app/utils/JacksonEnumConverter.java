package com.rottenpotatoes.app.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class JacksonEnumConverter implements GenericConverter {
    private ObjectMapper mapper;
    private Set<ConvertiblePair> set;

    @Autowired
    public JacksonEnumConverter(ObjectMapper mapper){
        this.mapper = mapper;
        this.set = new HashSet<>();
        this.set.add(new ConvertiblePair(String.class, Enum.class));
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return set;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if(source == null) return null;

        try {
            return mapper.readValue(source.toString(), targetType.getType());
        } catch (IOException e) {
            e.printStackTrace();
           return new Exception("Conversion Failed: "
                   + sourceType.getType() + " to " + targetType.getType());
        }
    }

}
