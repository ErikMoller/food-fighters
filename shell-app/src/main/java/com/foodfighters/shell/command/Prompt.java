package com.foodfighters.shell.command;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultPromptProvider;
import org.springframework.stereotype.Component;

/**
 * Created by erimol on 2017-03-25.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class Prompt extends DefaultPromptProvider {

    @Override
    public String getPrompt() {
        return "food-fighters-shell>";
    }


    @Override
    public String getProviderName() {
        return "My prompt provider";
    }

}
