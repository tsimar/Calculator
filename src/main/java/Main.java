import model.InputData;
import service.*;

import java.math.BigDecimal;

public class Main {

    private PrintingService printingService;

    public static void main(String[] args) {
        InputData inputData = new InputData()
                .withAmount(new BigDecimal("298000"));

        PrintingService printingService = new PrintingServiceImpl();
        RateCalculationService rateCalculationService= new RateCalculationServiceImpl(
                new TimePointServiceImpl(),
                new AmountsCalculationServiceImpl(),
                new ResidualCalculationServiceImpl()


        ) ;

        MortgageCalculationService mortgageCalculationService = new MortgageCalculationServiceImpl(
                printingService,
                rateCalculationService
        );
        mortgageCalculationService.calculate(inputData);

    }
}
