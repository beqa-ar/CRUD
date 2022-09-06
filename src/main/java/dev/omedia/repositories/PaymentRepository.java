package dev.omedia.repositories;

import dev.omedia.domains.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<Payment,Long> {
    Iterable<Payment> findPaymentByItem_Id(long item_id);
}
