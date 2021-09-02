package com.mohsinkd786.mapper;

import com.mohsinkd786.data.entities.Credentials;
import com.mohsinkd786.data.entities.Customer;
import com.mohsinkd786.dtos.CustomerDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        //super.configure(factory);
        factory.registerClassMap(factory.classMap(CustomerDto.class, Customer.class)
                .byDefault()
                .field("username","credentials.username")
                .field("password","credentials.password")
        );

        factory.registerClassMap(factory.classMap(CustomerDto.class, Credentials.class)
                .byDefault());
    }
}
