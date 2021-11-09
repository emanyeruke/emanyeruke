package zw.co.mynhaka.polad.service.iface.reports.dtos;

import zw.co.mynhaka.polad.domain.enums.PolicyType;

import java.math.BigDecimal;

public class CommissionsPerProductResult {
    String month;
    int year;
    PolicyType policy;
    BigDecimal total;
}
