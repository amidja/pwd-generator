package au.gov.australia.itsecurity.pwd.validator.rule;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import au.gov.australia.itsecurity.core.pwd.generator.CharacterType;
import au.gov.australia.itsecurity.core.pwd.generator.Password;
import au.gov.australia.itsecurity.core.pwd.validator.rule.CharacterRule;
import au.gov.australia.itsecurity.core.pwd.validator.rule.PasswordRule;
import au.gov.australia.itsecurity.core.pwd.validator.rule.PasswordValidator;
import au.gov.australia.itsecurity.core.pwd.validator.rule.RuleResult;
import au.gov.australia.itsecurity.core.pwd.validator.rule.RuleResultDetail;

public class PasswordValidatorTest {

	@Test
	public void testDigitCharacterRule(){
		CharacterRule testRule = new CharacterRule(CharacterType.DIGIT);
		
		assertThat(testRule.validate(new Password("111111")).isValid(), equalTo(true));
		assertThat(testRule.validate(new Password("aaaa")).isValid(), equalTo(false));		
	}
	
	
		
	@Test
	public void tesPwdRules(){		
		List<PasswordRule> pwdRules = new ArrayList<PasswordRule>();
		
		CharacterRule digitRule = new CharacterRule(CharacterType.DIGIT);
		digitRule.setNumberOfCharacters(1);
		digitRule.setMaxNumberOfCharacters(10);
		
		CharacterRule lowerCaseRule = new CharacterRule(CharacterType.LOWERCASE);
		lowerCaseRule.setNumberOfCharacters(1);
		lowerCaseRule.setMaxNumberOfCharacters(10);
		
		CharacterRule uperCaseRule = new CharacterRule(CharacterType.UPPERCASE);
		uperCaseRule.setNumberOfCharacters(1);
		uperCaseRule.setMaxNumberOfCharacters(10);
		
		CharacterRule specialCaseRule = new CharacterRule(CharacterType.NONALPHANUMERIC);
		specialCaseRule.setNumberOfCharacters(1);
		specialCaseRule.setMaxNumberOfCharacters(10);
		//
		pwdRules.add(digitRule);
		pwdRules.add(lowerCaseRule);
		pwdRules.add(uperCaseRule);
		pwdRules.add(specialCaseRule);
		
							
		PasswordValidator validator = new PasswordValidator(pwdRules);
		
		assertThat(validator.validate(new Password("1313aA#")).isValid(), equalTo(true));
		
		RuleResult result = validator.validate(new Password("1313aA"));
		for (RuleResultDetail details : result.getDetails()){
			System.out.println(details.getErrorCode());
		}
		
		
	}		


}
