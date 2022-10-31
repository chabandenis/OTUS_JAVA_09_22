package homework;


import java.util.*;

public class CustomerReverseOrder {

    //todo: 2. надо реализовать методы этого класса
    Stack<Customer> lst = new Stack<>();

    public void add(Customer customer) {
        lst.push(customer);
    }

    public Customer take() {
        return lst.pop(); // это "заглушка, чтобы скомилировать"
    }
}
