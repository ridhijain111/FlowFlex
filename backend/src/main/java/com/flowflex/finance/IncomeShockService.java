package com.flowflex.finance;

import com.flowflex.api.ApiModels.ShockStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeShockService {
    public ShockStatus assess(List<Double> lastSeven, List<Double> lastThirty) {
        double recent = average(lastSeven);
        double historical = average(lastThirty);
        boolean shock = historical > 0 && recent < historical * 0.5;
        return new ShockStatus(shock, round(recent), round(historical),
                shock ? "Income Shock Detected" : "Income is within its usual range",
                shock ? "Your recent earnings are significantly below your normal income." : "We will keep watching your income pattern and adjust when needed.",
                shock ? List.of("Pause aggressive loan repayment", "Recommend zero or minimal savings contribution", "Prioritise essential expenses") : List.of("Keep your current allocation plan", "Build your stability buffer on stronger days"));
    }
    private double average(List<Double> values) { return values.isEmpty() ? 0 : values.stream().mapToDouble(Double::doubleValue).average().orElse(0); }
    private double round(double value) { return Math.round(value * 100.0) / 100.0; }
}
