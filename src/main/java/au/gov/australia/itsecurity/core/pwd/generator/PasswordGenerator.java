package au.gov.australia.itsecurity.core.pwd.generator;

import java.util.ArrayList;
import java.util.List;

import au.gov.australia.itsecurity.core.pwd.validator.rule.CharacterRule;
import au.gov.australia.itsecurity.core.pwd.validator.rule.PasswordRule;
import au.gov.australia.itsecurity.core.pwd.validator.rule.PasswordValidator;
import au.gov.australia.itsecurity.core.pwd.validator.rule.RuleResult;

public class PasswordGenerator {

    private static final int allowedToFail = 1; 
  
    public PasswordGenerator() {
		super();	
	}
   
               
    public String getRandomPassword(int pwdLength) {
    	
        StringBuffer pwdStrBuff = new StringBuffer(pwdLength);       

        CharacterType[] charTypes = {CharacterType.DIGIT, CharacterType.LOWERCASE, CharacterType.UPPERCASE, CharacterType.NONALPHANUMERIC};        	
        StringBuffer pwdCharSet = getCharacters(charTypes);
            
        for (int inx = 0; inx < pwdLength; inx++) {             
        	int selChar = (int) (Math.random() * (pwdCharSet.length() - 1));
        	pwdStrBuff.append(pwdCharSet.charAt(selChar));                        	                                   
        }
            
       PasswordValidator pwdValidator = getPwdValidator(pwdLength);
       RuleResult result = pwdValidator.validate(new Password (pwdStrBuff.toString()), allowedToFail);
       if (result.isValid()){
    	   return pwdStrBuff.toString();   
       }else{
    	   return getRandomPassword(pwdLength);
       }       
    }
   
    private static StringBuffer getCharacters(CharacterType[] charTypes) {    	               
        String returnVal = CharacterType.getRandomizedCharacters(charTypes);
        return new StringBuffer(returnVal);               
    }
    
    private PasswordValidator getPwdValidator(int pwdLength){
    	
    	int maxNofCharacters = pwdLength / 2;
    	List<PasswordRule> pwdRules = new ArrayList<PasswordRule>();
		
		CharacterRule digitRule = new CharacterRule(CharacterType.DIGIT);
		digitRule.setNumberOfCharacters(1);
		digitRule.setMaxNumberOfCharacters(maxNofCharacters);
		
		CharacterRule lowerCaseRule = new CharacterRule(CharacterType.LOWERCASE);
		lowerCaseRule.setNumberOfCharacters(1);
		lowerCaseRule.setMaxNumberOfCharacters(maxNofCharacters);
		
		CharacterRule uperCaseRule = new CharacterRule(CharacterType.UPPERCASE);
		uperCaseRule.setNumberOfCharacters(1);
		uperCaseRule.setMaxNumberOfCharacters(maxNofCharacters);
		
		CharacterRule specialCaseRule = new CharacterRule(CharacterType.NONALPHANUMERIC);
		specialCaseRule.setNumberOfCharacters(1);
		specialCaseRule.setMaxNumberOfCharacters(maxNofCharacters);
		//
		pwdRules.add(digitRule);
		pwdRules.add(lowerCaseRule);
		pwdRules.add(uperCaseRule);
		pwdRules.add(specialCaseRule);
									
		return new PasswordValidator(pwdRules);    
    }
    
    
    public static void main(String[] args){
    	CharacterType[] charTypes = {CharacterType.DIGIT, CharacterType.LOWERCASE, CharacterType.UPPERCASE, CharacterType.NONALPHANUMERIC};
    	
    	System.out.println( "randomized characters = " + PasswordGenerator.getCharacters(charTypes));
    	System.out.println( "randomized characters = " + PasswordGenerator.getCharacters(charTypes));
    	PasswordGenerator pwdGen = new PasswordGenerator();
    	for (int i=0; i < 10; i++){
    		String password = pwdGen.getRandomPassword(10);
    		System.out.println( "randomized password = " + password + " , length = " + password.length());	
    	}
    	
    	
    }
}
