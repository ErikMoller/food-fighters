package com.foodfighters.shell.command;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultBannerProvider;
import org.springframework.shell.support.util.OsUtils;
import org.springframework.stereotype.Component;

/**
 * Created by erimol on 2017-03-24.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class Banner extends DefaultBannerProvider {

    public String getBanner() {
        StringBuffer buf = new StringBuffer();
        buf.append("=======================================" + OsUtils.LINE_SEPARATOR);
        buf.append("*                                     *"+ OsUtils.LINE_SEPARATOR);
        buf.append("*            Food Fighters Shell      *" +OsUtils.LINE_SEPARATOR);
        buf.append("*                                     *"+ OsUtils.LINE_SEPARATOR);
        buf.append("=======================================" + OsUtils.LINE_SEPARATOR);
        buf.append("Version:" + this.getVersion());
        return buf.toString();
    }

    public String getVersion() {
        return "1.2.3";
    }

    public String getWelcomeMessage() {
        return "Welcome to Food Fighters Shell";
    }

    @Override
    public String getProviderName() {
        return "Hello World Banner";
    }
}