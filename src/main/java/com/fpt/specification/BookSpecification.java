package com.fpt.specification;

import com.fpt.entity.Author;
import com.fpt.entity.Book;
import com.fpt.entity.Category;
import com.fpt.entity.Publisher;
import com.google.gson.Gson;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class BookSpecification implements Specification<Book> {

    private SearchCriteria criteria;

    public BookSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
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
            Join<Book, Author> bookAuthorJoin = root.join("author");
            Join<Book, Publisher> bookPublisherJoin = root.join("publisher");
            Join<Book, Category> bookCategoryJoin = root.join("categories");
            Predicate predicate = builder.or(
                    builder.like(bookAuthorJoin.get("name"), "%" + criteria.getValue() + "%"),
                    builder.like(bookAuthorJoin.get("description"), "%" + criteria.getValue() + "%"),
                    builder.like(bookPublisherJoin.get("name"), "%" + criteria.getValue() + "%"),
                    builder.like(bookCategoryJoin.get("name"), "%" + criteria.getValue() + "%")
            );
            return predicate;
        }
        return null;
    }
}
