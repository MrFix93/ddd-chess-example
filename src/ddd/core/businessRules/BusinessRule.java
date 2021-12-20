package ddd.core.businessRules;

import java.util.ArrayList;

/// <summary>
/// Represents a business rule in the domain (DDD).
/// </summary>
public abstract class BusinessRule
{
    /// <summary>
    /// Checks the business rule
    /// </summary>
    /// <returns>all violations against the rule</returns>
    public abstract ArrayList<BusinessRuleViolation> CheckRule();

    /// <summary>
    /// Checks the business rule and throws a BusinessRuleViolationException if it is not satified
    /// </summary>
    /// <exception cref="BusinessRuleViolationException">if the rule is not satified</exception>
    public void ThrowIfNotSatisfied()
    {
        ArrayList<BusinessRuleViolation> violations = CheckRule();

        if (violations != null && !violations.isEmpty())
        {
            throw new BusinessRuleViolationException(violations);
        }
    }

    /// <summary>
    /// Checks the business rule and throws a BusinessRuleViolationException if it is not satified
    /// </summary>
    /// <exception cref="BusinessRuleViolationException">if the rule is not satified</exception>
    public static void ThrowIfNotSatisfied(BusinessRule rule)
    {
        rule.ThrowIfNotSatisfied();
    }

    /// <summary>
    /// Combines this rule with another rule. Both rules must be satified to satisfy the combined rule.
    /// </summary>
    /// <param name="rule">another rule</param>
    /// <returns>a combined rule</returns>
    public BusinessRule And(BusinessRule rule)
    {
        return new AndBusinessRule(this, rule);
    }
}
