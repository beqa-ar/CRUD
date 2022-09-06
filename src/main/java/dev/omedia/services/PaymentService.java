package dev.omedia.services;

import dev.omedia.domains.Payment;
import dev.omedia.enums.LoanStatus;
import dev.omedia.exceptions.EntityNotFoundException;
import dev.omedia.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository repo;

    @Autowired
    public PaymentService(PaymentRepository repo) {
        this.repo = repo;
    }

    public Iterable<Payment> getPayments() {
        return repo.findAll();
    }

    public Payment getPayment(final long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Payment.class.getName()+"with id: "+id+" not found" ));
    }

    public Payment addPayment(final Payment payment) {
            return repo.save(payment);
    }

    public Payment updatePayment(final long id,final Payment payment) {
        if (repo.existsById(id)) {
            payment.setId(id);
            return repo.save(payment);
        } else {
            throw new EntityNotFoundException(Payment.class.getName() + "with id: " + id + " not found");
        }
    }
    public void removePaymentById(final long id) {
        repo.deleteById(id);
    }

    public  Iterable<Payment> findPaymentByItemId(long item_id){
       return repo.findPaymentByItem_Id(item_id);
    }
}
