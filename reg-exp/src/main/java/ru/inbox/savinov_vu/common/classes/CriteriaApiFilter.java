package ru.inbox.savinov_vu.common.classes;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;



public class CriteriaApiFilter<T> implements Specification<T> {

    protected List<Specifications> conditions;

    public CriteriaApiFilter() {
        this.conditions = new ArrayList<>();
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
        List<Predicate> predicates = buildPredicates(root, query, cb);
        return predicates.size() > 1
            ? cb.and(predicates.toArray(new Predicate[predicates.size()]))
            : predicates.get(0);
    }

    public void addCondition(Specifications<Specification> condition) {
        this.conditions.add(condition);
    }

    private List<Predicate> buildPredicates(Root root, CriteriaQuery query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        conditions.forEach(condition -> predicates.add(buildPredicate(condition, root, query, cb)));
        return predicates;
    }

    private Predicate buildPredicate(Specification condition, Root root, CriteriaQuery query, CriteriaBuilder cb) {
        return condition.toPredicate(root, query, cb);
    }

}
