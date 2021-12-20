package ddd.core.businessRules;


import java.util.ArrayList;

public class BusinessRuleViolationException extends RuntimeException {

    private final ArrayList<BusinessRuleViolation> _violations;

    public ArrayList<BusinessRuleViolation> getViolations() {
        return _violations;
    }

    public BusinessRuleViolationException() {
        _violations = new ArrayList<BusinessRuleViolation>();
    }

    public BusinessRuleViolationException(ArrayList<BusinessRuleViolation> violations) {
        super("Rule Violations: " + violations.size() + " violations have been detected.");
        _violations = violations;
    }
}
