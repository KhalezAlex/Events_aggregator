package org.aggregator.aggregator.model.entities;

public interface IUser {
    void message();
//    void reply();
    void vote(IUser user);
    void reclaim(IUser user);
}
