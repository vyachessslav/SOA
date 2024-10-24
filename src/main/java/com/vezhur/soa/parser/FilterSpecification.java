package com.vezhur.soa.parser;

import com.vezhur.soa.entity.LabworkEntity;
import org.springframework.data.jpa.domain.Specification;

public class FilterSpecification {

    public Specification<LabworkEntity> parseFilter(String filter) {
        if (filter == null || filter.isEmpty()) {
            return Specification.where(null);
        }

        String[] filterConditions = filter.split("&");
        Specification<LabworkEntity> specification = null;

        for (String condition : filterConditions) {
            String[] keyValue = condition.split("=");
            String field = keyValue[0];
            String[] operationValue = keyValue[1].split(":");
            String operation = operationValue[0];
            String value = operationValue[1];

            Specification<LabworkEntity> newSpec = buildSpecification(field, operation, value);
            specification = (specification == null) ? newSpec : specification.and(newSpec);
        }

        return specification;
    }

    private static Specification<LabworkEntity> buildSpecification(String field, String operation, String value) {
        switch (field) {
            case "minimalPoint":
                return buildMinimalPointSpecification(operation, value);
            // можно добавить другие поля для фильтрации аналогичным образом
            default:
                throw new IllegalArgumentException("Фильтрация по данному полю не поддерживается");
        }
    }

    private static Specification<LabworkEntity> buildMinimalPointSpecification(String operation, String value) {
        double pointValue = Double.parseDouble(value);
        switch (operation) {
            case "gte":
                return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("minimalPoint"), pointValue);
            case "lt":
                return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("minimalPoint"), pointValue);
            default:
                throw new IllegalArgumentException("Некорректная операция фильтрации для 'minimalPoint'");
        }
    }
}
