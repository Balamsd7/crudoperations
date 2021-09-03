package com.mohsinkd786.dtos;

import lombok.Data;

@Data
public class OrderDto {
    private String orderId;
    private String paymentId;
    private int noOfItems;
}
