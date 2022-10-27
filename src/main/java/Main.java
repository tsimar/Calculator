import model.InputData;
import model.RateType;
import service.*;

import java.math.BigDecimal;

public class Main {

    private PrintingService printingService;

    public static void main(String[] args) {
        InputData inputData = new InputData()
                .withAmount(new BigDecimal("298000"))
                .withMonthDuration(BigDecimal.valueOf(360))
                .withRateType(RateType.CONSTANT);

        PrintingService printingService = new PrintingServiceImpl();
        RateCalculationService rateCalculationService= new RateCalculationServiceImpl(
                new TimePointServiceImpl(),
                new AmountsCalculationServiceImpl(),
                new ResidualCalculationServiceImpl()


        ) ;

        MortgageCalculationService mortgageCalculationService = new MortgageCalculationServiceImpl(
                printingService,
                rateCalculationService,
                SummaryServiceFactory.create()
        );
        mortgageCalculationService.calculate(inputData);

    }
}
