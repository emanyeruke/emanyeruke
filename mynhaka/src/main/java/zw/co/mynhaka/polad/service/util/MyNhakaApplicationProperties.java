package zw.co.mynhaka.polad.service.util;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Setter
@Component
public class MyNhakaApplicationProperties {
//    public final @Value("${comprehensive.for.every.thousand}") double comprehensiveCostPerThousand;

    public final double accidentCostPerThousand;

//    public final @Value("${funeral.for.every.thousand}") double funeralCostPerThousand;

//    private final @Value("${savings.for.every.thousand}") double savingsCostPerThousand;

    public MyNhakaApplicationProperties(@Value("${accident.for.every.thousand}") String accidentCostPerThousand) {
        this.accidentCostPerThousand = Double.parseDouble(accidentCostPerThousand);
        System.out.println(accidentCostPerThousand);
    }
}