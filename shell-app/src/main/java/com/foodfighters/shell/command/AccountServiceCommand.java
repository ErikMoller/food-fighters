package com.foodfighters.shell.command;

import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

/**
 * Created by erimol on 2017-03-25.
 */
@Component
public class AccountServiceCommand implements CommandMarker {


    @CliCommand(value = "erik2", help = "Print a simple hello world message")
    public String simple(
            @CliOption(key = { "message" }, mandatory = true, help = "The hello world message") final String message,
            @CliOption(key = { "location" }, mandatory = false, help = "Where you are saying hello", specifiedDefaultValue="At work") final String location) {
        return "Message = [" + message + "] Location = [" + location + "]";
    }
}
