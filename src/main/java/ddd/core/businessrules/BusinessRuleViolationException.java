package main.java.ddd.core.businessrules;

import java.util.ArrayList;
import java.util.List;

public class BusinessRuleViolationException extends Exception {

    private final List<BusinessRuleViolation> violations;

    public List<BusinessRuleViolation> getViolations() {
        return violations;
    }

    public BusinessRuleViolationException() {
        violations = new ArrayList<>();
    }

    public BusinessRuleViolationException(List<BusinessRuleViolation> violations) {
        super("Rule Violations: " + violations.size() + " violations have been detected.");
        this.violations = violations;
    }
}
