package au.gov.australia.itsecurity.core.pwd.generator;

import static org.junit.Assert.*;

import org.junit.Test;

import au.gov.australia.itsecurity.core.pwd.generator.CharacterType;
import au.gov.australia.itsecurity.core.pwd.generator.PasswordGenerator;

public class PasswordGeneratorTest {

	@Test
	public void test() {
		CharacterType[] charTypes = {CharacterType.DIGIT, CharacterType.LOWERCASE, CharacterType.UPPERCASE, CharacterType.NONALPHANUMERIC};
    	    	
    	PasswordGenerator pwdGen = new PasswordGenerator();
    	for (int i=0; i < 10; i++){
    		String password = pwdGen.getRandomPassword(10);
    		System.out.println( "randomized password = " + password + " , length = " + password.length());	
    	}
	}

}
