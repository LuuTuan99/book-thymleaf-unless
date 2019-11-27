package com.fpt.specification;

import com.fpt.entity.Author;
import com.fpt.entity.Book;
import com.fpt.entity.Category;
import com.fpt.entity.Publisher;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class CategorySpecification implements Specification<Category> {
    private SearchCriteria criteria;

    public CategorySpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }


    @Override
    public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {

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
            Join<Category, Book> categoryBookJoin = root.join("books");

            Predicate predicate = builder.or(
                    builder.like(root.get("name"), "%" + criteria.getValue() + "%"),
                    builder.like(root.get("description"), "%" + criteria.getValue() + "%"),
                    builder.like(categoryBookJoin.get("name"), "%" + criteria.getValue() + "%"),
                    builder.like(categoryBookJoin.get("description"), "%" + criteria.getValue() + "%")
            );
            return predicate;
        }
        return null;
    }
}
