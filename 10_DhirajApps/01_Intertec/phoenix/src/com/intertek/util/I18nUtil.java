package com.intertek.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.intertek.locator.ServiceLocator;

public class I18nUtil
{
  public static Locale getLocale(HttpServletRequest request)
  {
    Locale locale = RequestContextUtils.getLocale(request);

    if(locale == null) locale = request.getLocale();

    return locale;
  }

  public static String getString(Locale locale, String key)
  {
    return getString(locale, key, key);
  }

  public static String getString(Locale locale, String key, String defaultValue)
  {
    return getString(locale, key, defaultValue, null);
  }

  public static String getString(Locale locale, String key, String defaultValue, Object[] params)
  {
    MessageSource messageSource = (MessageSource)ServiceLocator.getInstance().getBean("messageSource");

    if(messageSource != null)
    {
      try
      {
        return messageSource.getMessage(key, params, locale);
      }
      catch(Exception e)
      {
        //e.printStackTrace();
      }
    }

    return defaultValue;
  }
}
