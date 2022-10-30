package homework;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class CustomerService {

    private final Map<Customer, String> lst = new HashMap<>();

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        Map<Customer, String> lst2 = new HashMap<>();

        for (Map.Entry<Customer, String> entry : lst.entrySet()) {
            lst2.put(new Customer(entry.getKey().getId(),
                    entry.getKey().getName(),
                    entry.getKey().getScores()
            ), entry.getValue());
        }

        Map.Entry<Customer, String> forRet = null;

        for (Map.Entry<Customer, String> entry : lst2.entrySet()) {
            lst2.put(entry.getKey(), entry.getValue());
            Customer cst = entry.getKey();
            if (forRet == null) {
                forRet = entry;
            } else {
                if (cst.getScores() < forRet.getKey().getScores()) {
                    forRet = entry;
                }
            }
        }

        Map.Entry<Customer, String> ret = forRet;

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
