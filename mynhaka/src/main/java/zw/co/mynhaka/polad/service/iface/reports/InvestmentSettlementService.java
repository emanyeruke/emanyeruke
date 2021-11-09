package zw.co.mynhaka.polad.service.iface.reports;


import zw.co.mynhaka.polad.reports.claims.IAccidentClaimReport;

public interface InvestmentSettlementService {
    IAccidentClaimReport getSurrenderSettlementTime();

    IAccidentClaimReport getPartialWithdrawalTime();
}
