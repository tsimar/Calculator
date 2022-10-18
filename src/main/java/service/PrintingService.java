package service;

import model.InputData;

public interface PrintingService {
    String INTEREST_SUM = "SUMA ODSETEK: ";
    String RATE_NAMBER = "NR: ";
    String YEAR = "ROK: ";
    String MONTHS = " MIESIECY, ";
    String RATE = "RATA: ";
    String INTEREST = "OPROCENTOWANIE: ";
    String CAPITAL = "KAPITAL: ";
    String LEFT = "POZOSTALO: ";
    String MORTGAGE_AMOUNT = "KWOTA KREDYTU: ";
    String MORTGAGE_PERIOD = "OKRES KREDYTOWANIA: ";

    String CURRENCY = " ZL ";
    String NEW_LINE = "\n";
    String PERCENT = "% ";

    void printInputDataInfo(final InputData inputData);
}
