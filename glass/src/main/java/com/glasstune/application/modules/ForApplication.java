package com.glasstune.application.modules;

/**
 * Created by njackson on 29/12/14.
 */

import java.lang.annotation.Retention;
import javax.inject.Qualifier;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Retention(RUNTIME)
public @interface ForApplication {
}

