package service;

import model.InputData;
import model.Rate;
import model.Summary;

import java.util.List;

public interface PrintingService {

    String INTEREST_SUM = "SUMA ODSETEK: ";
    String OVERPAYMENT_PROVISION="PROWIZJA ZA NADPLATY";
    String LOSTS_SUM="LOSTS_SUM";

    String OVERPAYMENT_REDUCE_RATE="NADPLATA, ZMNIEJSZENIE RATY";
    String OVERPAYMENT_REDUCE_PERIOD="NADPLATA, SKROCENIE OKRESU";
    String OVERPAYMENT_FREQUENCY="SCHEMAT DOKONYWANIA NADPLAT";

    String RATE_NAMBER = "NR: ";
    String YEAR = "ROK: ";
    String MONTHS = " MIESIECY ";
    String DATE="DATA: ";
    String MONTH="MIESIAC: ";
    String RATE = "RATA: ";
    String INTEREST = "ODSETKI: ";
    String CAPITAL = "KAPITAL: ";
    String OVERPAYMENT="NADPLATA";
    String LEFT_AMOUNT = "PKWOTA: ";
    String LEFT_MONTHS = "PMSC: ";
    String MORTGAGE_AMOUNT = "KWOTA KREDYTU: ";
    String MORTGAGE_PERIOD = "OKRES KREDYTOWANIA: ";


    String CURRENCY = " ZL ";
    String NEW_LINE = "\n";
    String PERCENT = "% ";

    void printInputDataInfo(final InputData inputData);

    void printRates(List<Rate> rates);
    void printSummary(Summary summary);
}
