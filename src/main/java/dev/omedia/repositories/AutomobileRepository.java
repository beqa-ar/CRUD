package dev.omedia.repositories;

import dev.omedia.domains.Automobile;
import dev.omedia.enums.LoanStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Optional;

@Repository
public interface AutomobileRepository extends PagingAndSortingRepository<Automobile,Long> {
    Page<Automobile> findByOwnerPersonalNo(String ownerPersonalNo, Pageable pageable);
    Page<Automobile> findByLoanStatus(LoanStatus status, Pageable pageable);
}
