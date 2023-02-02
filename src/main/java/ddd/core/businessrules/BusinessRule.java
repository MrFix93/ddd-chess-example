package ddd.core.businessrules;

import java.util.List;

/**
 * Represents a business rule in the domain (DDD).
 */
public interface BusinessRule {
    /**
     * Checks the business rule.
     * @return All violations against the rule.
     */
    List<BusinessRuleViolation> checkRule();

    /**
     * Checks the business rule.
     * @throws BusinessRuleViolationException if the business rule is not satisfied.
     */
    default void throwIfNotSatisfied() throws BusinessRuleViolationException {
        var violations = checkRule();

        if (violations != null && !violations.isEmpty()) {
            throw new BusinessRuleViolationException(violations);
        }
    }

    /**
     * Checks the business rule.
     * @throws BusinessRuleViolationException if the business rule is not satisfied.
     * @param rule The business rule to be checked.
     */
    static void throwIfNotSatisfied(BusinessRule rule) throws BusinessRuleViolationException {
        rule.throwIfNotSatisfied();
    }

    /**
     * Combines this rule with another rule. Both rules must be satified to satisfy the combined rule.
     * @param rule Another rule.
     * @return A combined rule.
     */
    default BusinessRule and(BusinessRule rule) {
        return new AndBusinessRule(this, rule);
    }
}
