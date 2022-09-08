package dev.omedia.services;

import dev.omedia.domains.Automobile;
import dev.omedia.domains.Payment;
import dev.omedia.exceptions.EntityAlreadyExistsException;
import dev.omedia.exceptions.EntityNotFoundException;
import dev.omedia.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository repo;

    @Autowired
    public PaymentService(PaymentRepository repo) {
        this.repo = repo;
    }

    public Page<Payment> getPayments(final Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Payment getPayment(final long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Payment.class.getName() + "with id: " + id + " not found"));
    }

    public Payment addPayment(final Payment payment) {
        if(repo.existsById(payment.getId())){
            throw new EntityAlreadyExistsException(Payment.class.getName() + "with id: " + payment.getId() + " already exists");
        }
        return repo.save(payment);
    }

    public Payment updatePayment(final long id, final Payment payment) {
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

    public Page<Payment> findPaymentByItemId(final long itemId, final Pageable pageable) {
        return repo.findPaymentByItemId(itemId, pageable);
    }
}
