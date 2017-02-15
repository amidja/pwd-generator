package au.gov.australia.itsecurity.core.pwd.validator.rule;

import au.gov.australia.itsecurity.core.pwd.generator.Password;

public interface PasswordRule {

	  /**
	   * Validates the supplied password data per the requirements of this rule.
	   *
	   * @param  passwordData  to verify (not null).
	   *
	   * @return  details on password verification
	   *
	   * @throws  NullPointerException  if the rule data is null.
	   */
	  RuleResult validate(Password password);	  	  
}
