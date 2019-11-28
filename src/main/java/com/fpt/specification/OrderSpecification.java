package com.fpt.specification;

import com.fpt.entity.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class OrderSpecification implements Specification<OrderBook> {
    private SearchCriteria criteria;

    public OrderSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<OrderBook> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(">=")) {
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<=")) {
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("!=")) {
            return builder.notEqual(root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("=")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        } else if (criteria.getOperation().equalsIgnoreCase("join")) {
            Join<OrderBook, Member> orderBookMemberJoin = root.join("createdBy");
            Join<OrderBook, OrderDetails> orderBookOrderDetailsJoin = root.join("orderDetails");
            Predicate predicate = builder.or(
                    builder.like(root.get("nameOrder"), "%" + criteria.getValue() + "%"),
                    builder.like(root.get("customerName"), "%" + criteria.getValue() + "%"),
                    builder.like(root.get("customerAddress"), "%" + criteria.getValue() + "%"),
                    builder.like(root.get("customerPhone"), "%" + criteria.getValue() + "%"),
                    builder.like(orderBookMemberJoin.get("username"), "%" + criteria.getValue() + "%")
            );
            return predicate;
        }
        return null;
    }
}
