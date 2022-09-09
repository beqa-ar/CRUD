package dev.omedia.repositories;

import dev.omedia.domains.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends PagingAndSortingRepository<Payment,Long> {
    Page<Payment> findPaymentByItemId(long itemId, Pageable pageable);
}
