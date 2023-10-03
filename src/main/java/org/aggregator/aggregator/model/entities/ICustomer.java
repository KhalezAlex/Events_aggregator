package org.aggregator.aggregator.model.entities;

public interface ICustomer extends IUser{
    //добавить в аргументы исполнителя
    void addToCart(IExecutor executor);
    void getContacts();
    void getContacts(int days);
    void pay(double payment);
}
