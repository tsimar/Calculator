package service;

import model.InputData;
import model.Rate;
import model.RateAmounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AmountsCalculationServiceImpl implements AmountsCalculationService {

    private static final BigDecimal YEAR = BigDecimal.valueOf(12);

    @Override
    public RateAmounts calculate(InputData inputData) {
        switch (inputData.getRateType()) {
            case CONSTANT:
                return calculateConstantRate(inputData);
            case DECREASING:
                return calculateDecreasingRate(inputData);
            default:
                throw new RuntimeException("Case not handled");
        }

    }


    @Override
    public RateAmounts calculate(InputData inputData, Rate previousRate) {
        switch (inputData.getRateType()) {
            case CONSTANT:
                return calculateConstantRate(inputData, previousRate);
            case DECREASING:
                return calculateDecreasingRate(inputData, previousRate);
            default:
                throw new RuntimeException("Case not handled");
        }

    }

    private RateAmounts calculateConstantRate(InputData inputData) {
        BigDecimal interestPercent = inputData.getInterestPercent();
        BigDecimal residualAmount = inputData.getAmount();

        BigDecimal q = calculate(interestPercent);

        BigDecimal rateAmount = calculateConstantRateAmount(q, inputData.getAmount(), inputData.getMonthDuration());

        BigDecimal interestAmount = calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal capitalAmount = calculateCapitalAmount(rateAmount, interestAmount);
        return new RateAmounts(rateAmount, interestAmount, capitalAmount);
    }

    private RateAmounts calculateConstantRate(InputData inputData, Rate previousRate) {
        BigDecimal interestPercent = inputData.getInterestPercent();
        BigDecimal residualAmount = previousRate.getMortgageResidual().getAmount();

        BigDecimal q = calculate(interestPercent);

        BigDecimal rateAmount = calculateConstantRateAmount(q, inputData.getAmount(), inputData.getMonthDuration());
        BigDecimal interestAmount = calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal capitalAmount = calculateCapitalAmount(rateAmount, interestAmount);
        return new RateAmounts(rateAmount, interestAmount, capitalAmount);
    }

    private RateAmounts calculateDecreasingRate(InputData inputData) {
        BigDecimal interestPercent = inputData.getInterestPercent();
        BigDecimal residualAmount = inputData.getAmount();

        BigDecimal interestAmount = calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal capitalAmount = calculateDecreasingCapitalAmount(residualAmount, inputData.getMonthDuration());
        BigDecimal rateAmount =  capitalAmount.add(interestAmount);
        return new RateAmounts(rateAmount, interestAmount, capitalAmount);
    }

    private RateAmounts calculateDecreasingRate(InputData inputData, Rate previousRate) {
        BigDecimal interestPercent = inputData.getInterestPercent();
        BigDecimal residualAmount = previousRate.getMortgageResidual().getAmount();


        BigDecimal interestAmount = calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal capitalAmount = calculateDecreasingCapitalAmount(inputData.getAmount(), inputData.getMonthDuration());
        BigDecimal rateAmount = capitalAmount.add(interestAmount);

        return new RateAmounts(rateAmount, interestAmount, capitalAmount);
    }

    private BigDecimal calculate(BigDecimal interestPercent) {
        return interestPercent.divide(YEAR, 10, RoundingMode.HALF_UP).add(BigDecimal.ONE);
    }

    private BigDecimal calculateConstantRateAmount(BigDecimal q, BigDecimal amount, BigDecimal monthDuration) {
        return amount
                .multiply(q.pow(monthDuration.intValue()))
                .multiply(q.subtract(BigDecimal.ONE))
                .divide(q.pow(monthDuration.intValue()).subtract(BigDecimal.ONE), 2, RoundingMode.HALF_UP);
    }


    private BigDecimal calculateCapitalAmount(BigDecimal rateAmount, BigDecimal interestAmount) {
        return rateAmount.subtract(interestAmount);
    }

    private BigDecimal calculateInterestAmount(BigDecimal residualAmount, BigDecimal interestPercent) {
        return residualAmount.multiply(interestPercent).divide(YEAR, 2, RoundingMode.HALF_UP);

    }

    private BigDecimal calculateDecreasingCapitalAmount(BigDecimal amount, BigDecimal monthDuration) {
        return amount.divide(monthDuration, 2, RoundingMode.HALF_UP);
    }
}
