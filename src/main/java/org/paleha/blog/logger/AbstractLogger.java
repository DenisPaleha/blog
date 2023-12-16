package org.paleha.blog.logger;


import org.paleha.blog.exceptions.LoggerException;

public abstract class AbstractLogger {
    public abstract void logOutput(String result, String prefix) throws LoggerException;

}
