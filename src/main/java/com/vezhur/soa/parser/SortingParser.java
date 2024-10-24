package com.vezhur.soa.parser;

import com.vezhur.soa.exception.BadRequestException;
import org.springframework.data.domain.Sort;

public class SortingParser {

    public Sort parseSort(String sort) {

        if (sort == null || sort.isEmpty()) {
            return Sort.unsorted();
        }

        String[] orders = sort.split(",");
        Sort finalSort = Sort.unsorted();

        for (String order : orders) {
            order = order.trim();
            if (order.startsWith("desc(") && order.endsWith(")")) {
                finalSort = finalSort.and(Sort.by(Sort.Order.desc(order.substring(5, order.length() - 1))));
            } else if (order.startsWith("asc(") && order.endsWith(")")) {
                finalSort = finalSort.and(Sort.by(Sort.Order.asc(order.substring(4, order.length() - 1))));
            } else {
                throw new BadRequestException("Некорректные параметры запроса");
            }
        }

        return finalSort;
    }
}
