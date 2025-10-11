package com.eonis.demo.core.model;

import com.eonis.demo.persistence.enums.CartStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewCart {
    private String reviewerEmail;
    private Long cartId;
    private CartStatus status;
}
