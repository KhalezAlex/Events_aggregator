package org.aggregator.aggregator.entities;

public interface IExecutor extends IUser{
    void accept();
    void decline();
}
