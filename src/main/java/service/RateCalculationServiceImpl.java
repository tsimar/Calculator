package service;

import model.*;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class RateCalculationServiceImpl implements RateCalculationService {

    private final TimePointService timePointService;

    private final AmountsCalculationService amountsCalculationService;

    private final RasidualCalculationService rasidualCalculationService;

    public RateCalculationServiceImpl(
            TimePointService timePointService,
            AmountsCalculationService amountsCalculationService,
            RasidualCalculationService rasidualCalculationService
    ) {
        this.timePointService = timePointService;
        this.amountsCalculationService = amountsCalculationService;
        this.rasidualCalculationService = rasidualCalculationService;
    }

    @Override
    public List<Rate> calculate(InputData inputData) {
        List<Rate> rates = new LinkedList<>();
        BigDecimal rateNumber = BigDecimal.ONE;

        Rate firstRate = calculateRate(rateNumber, inputData);
        rates.add(firstRate);
        Rate previousRate = firstRate;
        for (BigDecimal index = rateNumber.add(BigDecimal.ONE);
             index.compareTo(inputData.getMonthDuration()) <= 0;
             index = index.add(BigDecimal.ONE)) {
            Rate nextRate = calculateRate(index, inputData, previousRate);
            rates.add(nextRate);
            previousRate = nextRate;
        }
        return rates;
    }

    private Rate calculateRate(BigDecimal rateNumber, InputData inputData) {
        TimePoint timePoint = timePointService.calculate(rateNumber,inputData);
        RateAmounts rateAmounts = amountsCalculationService.calculate(inputData);
        MortgageResidual mortgageResidual = rasidualCalculationService.calculate(rateAmounts,inputData);
        return new Rate(rateNumber, timePoint, rateAmounts, mortgageResidual);
    }

    private Rate calculateRate(BigDecimal rateNumber, InputData inputData, Rate previousRate) {
        TimePoint timePoint = timePointService.calculate(rateNumber,inputData);
        RateAmounts rateAmounts = amountsCalculationService.calculate(inputData,previousRate);
        MortgageResidual mortgageResidual = rasidualCalculationService.calculate(rateAmounts,previousRate);
        return new Rate(rateNumber, timePoint, rateAmounts, mortgageResidual);

    }
}
