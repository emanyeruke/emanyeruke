package zw.co.mynhaka.polad.events.payment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PolicyDocument {
    String policyNumber;
    String policyType;
}
