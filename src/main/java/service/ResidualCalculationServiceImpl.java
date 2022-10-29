package service;

import model.InputData;
import model.MortgageResidual;
import model.Rate;
import model.RateAmounts;

import java.math.BigDecimal;

public class ResidualCalculationServiceImpl implements RasidualCalculationService {
    @Override
    public MortgageResidual calculate(RateAmounts rateAmounts, InputData inputData) {

        BigDecimal residualAmount = calculateResidualAmount(rateAmounts, inputData.getAmount());
        BigDecimal residualDuration = inputData.getMonthDuration().subtract(BigDecimal.ONE);

        return new MortgageResidual(residualAmount, residualDuration);
    }

    @Override
    public MortgageResidual calculate(RateAmounts rateAmounts, Rate previousRate) {
        MortgageResidual residual = previousRate.getMortgageResidual();


        BigDecimal residualAmount = calculateResidualAmount(rateAmounts, residual.getAmount());
        BigDecimal residualDuration = residual.getDuration().subtract(BigDecimal.ONE);
        return new MortgageResidual(residualAmount, residualDuration);
    }

    private BigDecimal calculateResidualAmount(RateAmounts rateAmounts, BigDecimal amount) {
        return amount
                .subtract(rateAmounts.getCapitalAmount())
                .subtract(rateAmounts.getOverpayment().getAmount())
                .max(BigDecimal.ZERO);
    }
}
