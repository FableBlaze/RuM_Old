package ee.ut.cs.rum.frontpage;

import com.vaadin.data.Validator;
import com.vaadin.ui.PasswordField;

@SuppressWarnings("serial")
public class RePasswordValidator implements Validator {
	private PasswordField accPassword;
	private PasswordField rePassword;
	private String errorMessage;
	
	public RePasswordValidator(String errorMessage, PasswordField accPassword, PasswordField rePassword) {
		this.accPassword = accPassword;
		this.rePassword = rePassword;
		this.errorMessage = errorMessage;
	}
	
	@Override
	public void validate(Object value) throws InvalidValueException {
		if (!accPassword.getValue().equals(rePassword.getValue())) {
			throw new InvalidValueException(errorMessage);
		}
	}
    
	
}
