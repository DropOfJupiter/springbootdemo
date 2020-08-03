package com.example.qiutt.demo.utils;


import com.example.qiutt.demo.enums.ChargeUnitEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Objects;

/**
 * TODO
 *
 * @author yaowk
 * @date 2020-06-22
 */
@Slf4j
@Component
public class I18nUtils implements ApplicationContextAware,InitializingBean {

	private static ApplicationContext applicationContext;


	@Value("${language:zh}")
	private String locale;

	public static Locale defaultLocale;


	@Override
	public void afterPropertiesSet() throws Exception {
		String language = applicationContext.getEnvironment().getProperty("language");
		if (language != null) {
			defaultLocale = Locale.forLanguageTag(language);
		}
		if(Objects.isNull(defaultLocale)|| StringUtils.isEmpty(defaultLocale.getLanguage())){
			defaultLocale=Locale.ENGLISH;
		}
		log.info("defaultLocale:{}",defaultLocale.getLanguage());
		log.info("locale:{}",locale);
	}

	/**
	 * 翻译时间相关
	 */
	public static StringBuilder translationTime(long time, ChargeUnitEnum chargeUnitEnum){
		StringBuilder stringBuilder=new StringBuilder();
		switch(defaultLocale.getLanguage()){
		    case "en":
				stringBuilder.append(time).append(chargeUnitEnum.getCode());
				//复数格式要加s
		    	if(time>1){
					stringBuilder.append("s");
				}
		        break;
			case "zh":
				stringBuilder.append(time).append(chargeUnitEnum.getLabel());
				break;
		    default:
				break;
		}
		return stringBuilder;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		I18nUtils.applicationContext = applicationContext;
	}

}
