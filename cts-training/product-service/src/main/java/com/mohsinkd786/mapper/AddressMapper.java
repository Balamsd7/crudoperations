package com.mohsinkd786.mapper;

import com.mohsinkd786.data.entities.Address;
import com.mohsinkd786.dtos.AddressDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.registerClassMap(factory.classMap(Address.class, AddressDto.class)
                .byDefault()
                .field("city","city")
                .field("street","streetName")
                .field("zipCode","pinCode")
        );
    }
}
