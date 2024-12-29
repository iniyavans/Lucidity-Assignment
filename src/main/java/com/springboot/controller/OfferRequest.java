package com.springboot.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferRequest {
    public OfferRequest(int i, String string, int j, List<String> segments) {
		// TODO Auto-generated constructor stub
	}

	private int restaurant_id;
    private String offer_type;
    private int offer_value;

    private List<String> customer_segment;
}
