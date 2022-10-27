package service;

import model.InputData;
import model.Rate;
import model.Summary;

import java.util.List;

public class MortgageCalculationServiceImpl implements MortgageCalculationService {
    private final PrintingService printingService;

    private final RateCalculationService rateCalculationService;

    private final SummeryService summeryService;

    public MortgageCalculationServiceImpl(
            PrintingService printingService,
            RateCalculationService rateCalculationService,
            SummeryService summeryService
    ) {
        this.printingService = printingService;
        this.rateCalculationService = rateCalculationService;
        this.summeryService=summeryService;
    }

    @Override
    public void calculate(InputData inputData) {
//        PrintingService printingService=new PrintingServiceImpl();
        printingService.printInputDataInfo(inputData);
        List<Rate> rates = rateCalculationService.calculate(inputData);
        Summary summery=summeryService.calculate(rates);
        printingService.printSummary(summery);
        printingService.printRates(rates);
    }


}
