package com.strabl.service.notification.gateway;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.RawMessage;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;
import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.StrablException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

@Slf4j
@Component
@AllArgsConstructor
public class AmazonSESGateway {

  private static final String CHARSET_UTF_8 = "UTF-8";
  private static final String CONTENT_TYPE = "text/html";

  private final AmazonSimpleEmailService client;

  public void send(String from, List<String> to, String subject, String emailContent) {

    try {
      Session session = Session.getInstance(new Properties());
      MimeMessage mimeMessage = new MimeMessage(session);
      mimeMessage.setSubject(subject, CHARSET_UTF_8);
      mimeMessage.setText(emailContent, CHARSET_UTF_8);
      mimeMessage.addHeader("Content-Type", CONTENT_TYPE);

      // FROM
      mimeMessage.setFrom(new InternetAddress(from));

      // TO
      InternetAddress[] toAddresses = getInternetAddresses(to);
      mimeMessage.setRecipients(javax.mail.Message.RecipientType.TO, toAddresses);

//      // CC
//      String ccAddress = null;
//      if ((!StringUtils.isEmpty(ccAddress)) && EmailValidator.getInstance().isValid(ccAddress)) {
//        mimeMessage.setRecipient(
//            javax.mail.Message.RecipientType.CC, new InternetAddress(ccAddress));
//      }
//
//      // BCC
//      String bccAddress = null;
//      if ((!StringUtils.isEmpty(bccAddress)) && EmailValidator.getInstance().isValid(bccAddress)) {
//        List<InternetAddress> internetAddresses = new ArrayList<>();
//        internetAddresses.add(new InternetAddress(bccAddress));
//        mimeMessage.setRecipients(
//            javax.mail.Message.RecipientType.BCC,
//            internetAddresses.toArray(new InternetAddress[internetAddresses.size()]));
//      }

      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

      mimeMessage.writeTo(outputStream);
      RawMessage rawMessage = new RawMessage(ByteBuffer.wrap(outputStream.toByteArray()));
      SendRawEmailRequest rawEmailRequest = new SendRawEmailRequest(rawMessage);
      client.sendRawEmail(rawEmailRequest);
    } catch (AmazonClientException | javax.mail.MessagingException | IOException e) {
      log.error("Error in sending email", e);
      throw StrablException.of(ResponseType.EMAIL_NOT_SENT);
    }
  }

  private InternetAddress[] getInternetAddresses(List<String> to) {

    return to.stream()
        .filter(email -> EmailValidator.getInstance().isValid(email))
        .map(
            email -> {
              try {
                return new InternetAddress(email);
              } catch (AddressException e) {
                return null;
              }
            })
        .filter(Objects::nonNull).toArray(InternetAddress[]::new);
  }
}
