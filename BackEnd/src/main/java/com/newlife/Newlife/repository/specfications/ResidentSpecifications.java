package com.newlife.Newlife.repository.specfications;

import com.newlife.Newlife.entity.Resident;
import com.newlife.Newlife.entity.Resident_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ResidentSpecifications {

    public static Specification<Resident> nameLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Resident_.name), "%"+info+"%");
    }

    public static Specification<Resident> likeGenericQuery(String queryString) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>(1);
            predicates.add(nameLike(queryString).toPredicate(root, query, criteriaBuilder));
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }
}
