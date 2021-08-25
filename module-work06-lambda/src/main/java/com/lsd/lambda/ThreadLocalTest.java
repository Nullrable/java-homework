package com.lsd.lambda;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author nhsoft.lsd
 */
public class ThreadLocalTest {

    private ThreadLocal<DateFormat> dateFormat = ThreadLocal.withInitial(SimpleDateFormat::new);
}
