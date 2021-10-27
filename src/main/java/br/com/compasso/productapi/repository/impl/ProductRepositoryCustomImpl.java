package br.com.compasso.productapi.repository.impl;

import br.com.compasso.productapi.model.Product;
import br.com.compasso.productapi.repository.ProductRepositoryCustom;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    public static final String FIELD_NAME = "name";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_PRICE = "price";
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> search(String query, BigDecimal minPrice, BigDecimal maxPrice) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cr = cb.createQuery(Product.class);
        Root<Product> product = cr.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        Path<String> namePath = product.get(FIELD_NAME);
        Path<String> descriptionPath = product.get(FIELD_DESCRIPTION);
        Path<BigDecimal> pricePath = product.get(FIELD_PRICE);

        if (StringUtils.hasText(query)) {
            predicates.add(cb.or(cb.like(cb.upper(namePath), "%"+query.toUpperCase()+"%"),
                    cb.like(cb.upper(descriptionPath), "%"+query.toUpperCase()+"%")));
        }

        if (minPrice != null) {
            predicates.add(cb.greaterThanOrEqualTo(pricePath, minPrice));
        }

        if (maxPrice != null) {
            predicates.add(cb.lessThanOrEqualTo(pricePath, maxPrice));
        }

        cr.select(product).where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(cr).getResultList();
    }
}