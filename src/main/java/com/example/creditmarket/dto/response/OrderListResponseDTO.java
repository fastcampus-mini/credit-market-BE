package com.example.creditmarket.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderListResponseDTO {

    private List<OrderResponseDTO> list;
    private int totalNum;

    @Builder
    public OrderListResponseDTO(List<OrderResponseDTO> list, int totalNum) {
        this.list = list;
        this.totalNum = totalNum;
    }
}
