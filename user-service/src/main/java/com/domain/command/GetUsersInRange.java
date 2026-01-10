package com.domain.command;

import lombok.Value;

@Value(staticConstructor = "commandOf")
public class GetUsersInRange implements UserCommand {
    int fromIndex;
    int toIndex;
}
