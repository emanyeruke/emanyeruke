package zw.co.mynhaka.polad.domain.dtos.reports;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DateRange {
    private LocalDate startDate;
    private LocalDate endDate;
}
