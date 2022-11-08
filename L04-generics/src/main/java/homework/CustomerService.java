package homework;


import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public final class CustomerService {


    private final TreeMap<Customer, String> lst = new TreeMap<>(Comparator.comparingLong(o -> o.getScores()));

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        return new AbstractMap.SimpleEntry<Customer, String>(
                new Customer(lst.firstEntry().getKey().getId(), lst.firstEntry().getKey().getName(), lst.firstEntry().getKey().getScores()),
                lst.firstEntry().getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return lst.higherEntry(customer);
    }

    public void add(Customer customer, String data) {
        lst.put(customer, data);
    }
}
