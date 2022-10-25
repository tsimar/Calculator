package service;

import model.InputData;
import model.MortgageResidual;
import model.Rate;
import model.RateAmounts;

public interface RasidualCalculationService {
    MortgageResidual calculate(RateAmounts rateAmounts, InputData inputData);
    MortgageResidual calculate(RateAmounts rateAmounts, Rate previousRate);
}
