package com.mohsinkd786.mapper;

import com.mohsinkd786.data.entities.Orders;
import com.mohsinkd786.dtos.OrderDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class OrdersMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.registerClassMap(factory.classMap(Orders.class, OrderDto.class)
                .byDefault()
                .field("orderId","orderId")
                .field("paymentId","paymentId")
                .field("noOfItems","noOfItems")
        );
    }
}
