package homework;


import java.util.*;

public class CustomerReverseOrder {

    //todo: 2. надо реализовать методы этого класса
    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    TreeSet lst = new TreeSet();

    public void add(Customer customer) {
        lst.add(customer);
    }

    public Customer take() {

       return null; // это "заглушка, чтобы скомилировать"
    }
}
