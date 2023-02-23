package com.example.creditmarket.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderSaveRequestDTO {

    List<String> productIds;

}
