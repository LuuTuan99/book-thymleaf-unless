package com.fpt.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderDetailsId implements Serializable {
    @Column(name = "order_id", insertable = false, updatable = false)
    private long orderId;
    @Column(name = "book_id", insertable = false, updatable = false)
    private long bookId;

    public OrderDetailsId() {
    }

    public OrderDetailsId(long orderId, long bookId) {
        this.orderId = orderId;
        this.bookId = bookId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }
}
