package homework;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CustomerService {

    Map<Customer, String> lst = new HashMap<>();

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        Map.Entry<Customer, String> forRet = null;

        for (Map.Entry entry : lst.entrySet()) {
            Customer cst = (Customer) entry.getKey();
            if (forRet == null) {
                forRet = entry;
            } else {
                if (cst.getScores() < forRet.getKey().getScores()) {
                    forRet = entry;
                }
            }
        }

        return forRet; // это "заглушка, чтобы скомилировать"
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> forRet = null;

        System.out.println(
                lst.entrySet()
                        .stream()
                        .filter(e -> e.getKey().getScores() > customer.getScores())
                        .collect(Collectors.toList())
        );

        for (Map.Entry entry : lst.entrySet()
                .stream()
                .filter(e -> e.getKey().getScores() > customer.getScores())
                .collect(Collectors.toList())
        ) {
            Customer cst = (Customer) entry.getKey();
            if (forRet == null) {
                forRet = entry;
            } else {
                if (cst.getScores() < forRet.getKey().getScores()) {
                    forRet = entry;
                }
            }
        }

        return forRet; // это "заглушка, чтобы скомилировать"
    }

    public void add(Customer customer, String data) {
        lst.put(customer, data);
    }
}
