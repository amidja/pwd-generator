package au.gov.australia.itsecurity.core.pwd.generator;

public interface CharacterValidationType {
	
	public String getName();
	
	public String getValidCharacters();
	
	public ValidationErrorType getValidationErrorType();	
}
