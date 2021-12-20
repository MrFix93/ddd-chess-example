package ddd.core.businessRules;


import java.util.ArrayList;

class AndBusinessRule extends BusinessRule {
    private final BusinessRule _firstRule;
    private final BusinessRule _secondRule;

    public AndBusinessRule(BusinessRule firstRule, BusinessRule secondRule) {
        _firstRule = firstRule;
        _secondRule = secondRule;
    }

    public ArrayList<BusinessRuleViolation> CheckRule() {
        ArrayList<BusinessRuleViolation> result = new ArrayList<BusinessRuleViolation>();
        result.addAll(_firstRule.CheckRule());
        result.addAll(_secondRule.CheckRule());
        return result;
    }
}