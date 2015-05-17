package utils;

import model.Contact;
import play.libs.mailer.Email;
import play.libs.mailer.MailerPlugin;
import play.twirl.api.Html;
import views.html.mailTemplate.body;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Emails {

	public static void sendMail(Contact contact) {
		Config mailConfig = ConfigFactory.load("mail.conf");
		
		Email email = new Email();
		email.setSubject(mailConfig.getString("mail.subject"));
		email.setFrom(mailConfig.getString("mail.from"));
		
		Html emailBody = body.render(contact);
		email.setBodyHtml(emailBody.body());

		email.addTo(mailConfig.getString("mail.to"));
		MailerPlugin.send(email);
	}
}
