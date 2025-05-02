package com.pratik.payGateBlend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
public class Settings {

	@Value("${grips.three.key}")
	private String gripsThreeKey;

	@Value("${grips.three.iv}")
	private String gripsThreeIv;

	@Value("${grips.response.url}")
	private String gripsResponseUrl;

	@Value("${easebuzz.payment.url}")
	private String easebuzzPaymentUrl;

	@Value("${easebuzz.payment.salt}")
	private String easebuzzPaymentSalt;

}
