package model;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;

public class Contact {
	
	@Required
	@MinLength(value = 3)
	public String name;
	
	@Required
	@Email
	public String email;
	
	@Required
	@MinLength(value = 5)
	public String address;
	
	@Required
	@MinLength(value = 10)
	public String message;
}
