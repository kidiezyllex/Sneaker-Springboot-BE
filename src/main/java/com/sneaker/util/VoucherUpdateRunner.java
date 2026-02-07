package com.sneaker.util;

import com.sneaker.entity.Voucher;
import com.sneaker.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class VoucherUpdateRunner implements CommandLineRunner {

    private final VoucherRepository voucherRepository;

    @Override
    public void run(String... args) throws Exception {
        try {
            log.info("Checking for expired but ACTIVE vouchers...");

            LocalDateTime now = LocalDateTime.now();
            List<Voucher> vouchers = voucherRepository.findAll();

            long updatedCount = vouchers.stream()
                    .filter(v -> v.getStatus() == Voucher.Status.ACTIVE && v.getEndDate().isBefore(now))
                    .peek(v -> {
                        v.setStartDate(LocalDateTime.of(2025, 1, 1, 0, 0));
                        v.setEndDate(LocalDateTime.of(2026, 12, 31, 23, 59, 59));
                        v.setUpdatedAt(now);
                    })
                    .count();

            if (updatedCount > 0) {
                voucherRepository.saveAll(vouchers);
                log.info("Successfully updated {} expired vouchers to be valid until end of 2026.", updatedCount);
            } else {
                log.info("No expired ACTIVE vouchers found.");
            }
        } catch (Exception e) {
            log.warn(
                    "Could not check/update vouchers: {}. This is expected if the database is not yet fully initialized.",
                    e.getMessage());
        }
    }
}
