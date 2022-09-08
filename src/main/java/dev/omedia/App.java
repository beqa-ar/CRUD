package dev.omedia;

import dev.omedia.domains.Item;
import dev.omedia.domains.Payment;
import dev.omedia.enums.LoanStatus;
import dev.omedia.repositories.ItemRepository;
import dev.omedia.services.ItemService;
import dev.omedia.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@SpringBootApplication
public class App {
    @Autowired
    private ItemService is;

    @Autowired
    private PaymentService ps;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Scheduled(cron = "1 0 0 * * *")
    public void loanCheck() {
        Page<Item> items = is.getItemsByLoanStatus(LoanStatus.ACTIVE, Pageable.unpaged());
        items.forEach(item -> {
            double totalPaid = ps.findPaymentByItemId(item.getId(), Pageable.unpaged()).get()
                    .map(Payment::getAmountMoneyPaid)
                    .reduce(0d, Double::sum);
            double shouldPaid = item.getMonthlyPayment() * ChronoUnit.MONTHS.between(item.getLoanStartDate(), LocalDate.now());
            if (totalPaid < shouldPaid) {
                item.setLoanStatus(LoanStatus.CONFISCATED);
            } else if (totalPaid >= item.getAmountMoneyPaid()) {
                item.setLoanStatus(LoanStatus.CLOSED);
            }
        });
    }

}
