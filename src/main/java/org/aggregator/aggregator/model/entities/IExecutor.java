package org.aggregator.aggregator.model.entities;

public interface IExecutor extends IUser{
    void accept();
    void decline();
}
