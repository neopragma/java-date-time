# java-date-time

A small sample project to illustrate different ways to isolate Java code from the system clock.

## package com.neopragma.javadatetime.legacy

This represents a "legacy" implementation such as you might find in an existing code base. It illustrates a common problem when application code is not isolated from the system clock: It's hard to test functionality that depends on dates and times.

One of the key requirements for automating a test case is that the case is _repeatable_. Repeatability means test fixtures and input values are identical across all executions of the case. As the actual current time is a moving target, it's difficult to make test cases repeatable when they are dependent on the system clock. (Actually, "difficult" is a gentle way of putting it.)

Issues with tight coupling to the system clock include:

- hard to create repeatable tests (whether manual or automated)
- hard to manage behavior around edge cases; that is, the boundaries of a given time interval (such as an expiration date/time) that may be relevant to the functionality of the application
- hard to check behavior around the passage of time; controlling the clock ticks that occur between two events
- hard to re-run applications that are dependent on current date/time (for example, when a month-end closing job fails and must be re-run the next day)
- hard to ensure correct functionality when multiple time zones are involved

This project only addresses the problem of writing repeatable unit tests for code that depends on the system date/time.

The ```legacy``` implementation doesn't have a repeatable unit test case. You must change the test date to match the actual system date before running the test. This is not suitable for test automation.

## package com.neopragma.javadatetime.injection

This is a refactored version of the same sample code base as in the ```legacy``` package. Using an implementation of ```java.time.Clock```, it injects the current time into application code. This approach can be used when you have a dependency injection mechanism, and when you are using Java 8 or later. For earlier versions of Java, you can use ```jodatime``` to implement the same approach, or you can follow the approach given below in the ```prejava8``` example.

## package com.neopragma.javadatetime.fixedclock

This version of the sample code uses ```java.time.Clock``` but implements static utility methods rather than using dependency injection. You can control the logical "current time" returned by the ```LocalDateTime.now()``` method, and you can control whether clock ticks occur. 

This solution is based on an idea by Luiz Signorelli, documented here: http://stackoverflow.com/questions/24491260/mocking-time-in-java-8s-java-time-api 

## package com.neopragma.javadatetime.prejava8

This implementation uses dependency injection, but doesn't depend on the ```java.time``` package. In case you need to refactor an older code base that can't be brought up to date with Java 8, you can use this approach. Some organizations don't allow us to download packages unless their internal security group has vetted them; thus, there may be situations in which you can't use ```jodatime``` to remediate older code. This solution supports injection, but doesn't offer thread safety automatically and doesn't provide a means to control clock ticks. You can, at least, get some level of unit test automation in place.

This solution is based on an idea by John O'Hanley, documented here: http://www.javapractices.com/topic/TopicAction.do?Id=234 

