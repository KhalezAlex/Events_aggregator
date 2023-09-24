package org.aggregator.aggregator.entities;

interface IUser {
    void message();
//    void reply();
    void vote(IUser user);
    void reclaim(IUser user);
}
