package com.strabl.sdk.domain.criteria.specification;

import com.strabl.sdk.common.dto.page.UserFilterAttributes;
import com.strabl.sdk.domain.entity.Product;
import com.strabl.sdk.domain.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserSpecification {

    private UserSpecification() {}


  public static Specification<User> prepare(UserFilterAttributes filterAttributes) {

    return (Specification<User>)
        (root, cq, cb) -> {
          final Collection<Predicate> predicates = new ArrayList<>();

          if (StringUtils.isNotBlank(filterAttributes.getKeyword())) {
            predicates.add(
                cb.or(cb.like(root.get("fullName"), "%" + filterAttributes.getKeyword() + "%")));
          }

          if (filterAttributes.getId() != null) {
            predicates.add(cb.equal(root.get("id"), filterAttributes.getId()));
          }

          return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
  }

}
