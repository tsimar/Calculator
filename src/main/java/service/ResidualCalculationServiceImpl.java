package service;

import model.InputData;
import model.MortgageResidual;
import model.Rate;
import model.RateAmounts;

import java.math.BigDecimal;

public class ResidualCalculationServiceImpl implements RasidualCalculationService {
    @Override
    public MortgageResidual calculate(RateAmounts rateAmounts, InputData inputData) {

        BigDecimal residualAmount = inputData.getAmount().subtract(rateAmounts.getCapitalAmount()).max(BigDecimal.ZERO);
        BigDecimal residualDuration = inputData.getMonthDuration().subtract(BigDecimal.ONE);

        return new MortgageResidual(residualAmount, residualDuration);
    }

    @Override
    public MortgageResidual calculate(RateAmounts rateAmounts, Rate previousRate) {
        MortgageResidual residual = previousRate.getMortgageResidual();


        BigDecimal residualAmount = residual.getAmount().subtract(rateAmounts.getCapitalAmount()).max(BigDecimal.ZERO);
        BigDecimal residualDuration = residual.getDuration().subtract(BigDecimal.ONE);
        return new MortgageResidual(residualAmount, residualDuration);
    }
}
