package com.example.demo.service.common;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * メール送信Service実装クラス
 * 
 * @author ys-fj
 *
 */
@Service
@RequiredArgsConstructor
public class MailSendServiceImpl implements MailSendService {

	/** メール送信用クラス */
	private final MailSender mailSender;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean sendMail(String mailTo, String mailSubject, String mailText) {
		var smm = new SimpleMailMessage();
		smm.setTo(mailTo);
		smm.setSubject(mailSubject);
		smm.setText(mailText);

		try {
			mailSender.send(smm);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}
