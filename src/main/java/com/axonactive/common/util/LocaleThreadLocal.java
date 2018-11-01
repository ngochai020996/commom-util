/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.axonactive.common.util;

import java.util.Locale;

/**
 *
 * @author nvmuon
 */
public class LocaleThreadLocal {
	
	private LocaleThreadLocal() {}
	
    public static final ThreadLocal<Locale> THREAD_LOCAL = new ThreadLocal<>();

    public static Locale get() {
        return (THREAD_LOCAL.get() == null) ? Locale.getDefault() : THREAD_LOCAL.get();
    }

    public static void set(Locale locale) {
        THREAD_LOCAL.set(locale);
    }

    public static void unset() {
        THREAD_LOCAL.remove();
    }
}
