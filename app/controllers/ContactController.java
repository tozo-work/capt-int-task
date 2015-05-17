package controllers;

import static play.data.Form.form;
import model.Contact;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Emails;
import views.html.confirm;
import views.html.contact;

public class ContactController extends Controller {

	final static Form<Contact> contactForm = form(Contact.class);
	
	@play.filters.csrf.AddCSRFToken
    public static Result index() {
        return ok(contact.render(contactForm));
    }
    
	@play.filters.csrf.RequireCSRFCheck
    public static Result submit() {
    	Form<Contact> form = contactForm.bindFromRequest();
    	
    	if(form.hasErrors()) {
            return badRequest(contact.render(form));
        } else {
            Contact result = form.get();
            
            Emails.sendMail(result);
            
            return ok(confirm.render(result)); 
        }
    }
}
