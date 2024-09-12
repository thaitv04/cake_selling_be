package org.example.banbanh_be.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.banbanh_be.model.Cake;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CakeSpec implements Specification<Cake> {
    private CakeDto dto;

    public CakeSpec(CakeDto dto) {
        this.dto = dto;
    }

    @Override
    public Predicate toPredicate(Root<Cake> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        // chứa danh sách các điều kiện
        List<Predicate> predicateList = new ArrayList<Predicate>();

        if (dto.getName() != null) {
            predicateList.add(criteriaBuilder.like(root.get("name"),"%" + dto.getName() + "%"));
        }

        if (dto.getTypeIdCake() != 0) {
            predicateList.add(criteriaBuilder.equal(root.join("typeOfCake").get("id"), dto.getTypeIdCake()));
        }

        if (dto.getPrice() != 0) {
            predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), dto.getPrice()));
        }
        if (!predicateList.isEmpty()) {
            query.where(predicateList.toArray(new Predicate[0]));
        }
        return query.getRestriction();
    }
}