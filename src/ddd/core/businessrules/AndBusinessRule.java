package ddd.core.businessrules;

import java.util.ArrayList;
import java.util.List;

class AndBusinessRule implements BusinessRule {
    private final BusinessRule firstRule;
    private final BusinessRule secondRule;

    public AndBusinessRule(BusinessRule firstRule, BusinessRule secondRule) {
        this.firstRule = firstRule;
        this.secondRule = secondRule;
    }

    @Override
    public List<BusinessRuleViolation> checkRule() {
        List<BusinessRuleViolation> result = new ArrayList<>();
        result.addAll(firstRule.checkRule());
        result.addAll(secondRule.checkRule());
        return result;
    }
}