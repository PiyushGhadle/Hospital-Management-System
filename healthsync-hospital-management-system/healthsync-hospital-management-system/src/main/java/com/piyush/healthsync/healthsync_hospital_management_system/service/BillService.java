package com.piyush.healthsync.healthsync_hospital_management_system.service;

import com.piyush.healthsync.healthsync_hospital_management_system.models.Bill;
import com.piyush.healthsync.healthsync_hospital_management_system.repository.BillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    private static final Logger logger = LoggerFactory.getLogger(BillService.class);

    public Page<Bill> getAllBills(int page, int pageSize) {
        try {
            Pageable pageable = PageRequest.of(page, pageSize);
            return billRepository.findAll(pageable);
        } catch (Exception e) {
            logger.error("An error occurred while fetching all bills : {}", e.getMessage());
            return null;
        }
    }

    public Bill getBillById(Long id) {
        try {
            return billRepository.findById(id).orElse(null);
        } catch (Exception e) {
            logger.error("An error occurred while fetching bills by Id {}: {}",id, e.getMessage());
            return null;
        }
    }

    public Bill createBill(Bill bill) {
        try {
            return billRepository.save(bill);
        } catch (Exception e) {
            logger.error("An error occurred while create bills : {}", e.getMessage());
            return null;
        }
    }

    public Bill updateBill(Long id, Bill bill) {
        try {
            Optional<Bill> existingBill = billRepository.findById(id);
            if (existingBill.isPresent()) {
                Bill oldBill = existingBill.get();
                oldBill.setAmount(bill.getAmount());
                oldBill.setStatus(bill.getStatus());
                oldBill.setPatientId(bill.getPatientId());
                return  billRepository.save(oldBill);
            }else
                return null;
        } catch (Exception e) {
            logger.error("An error occurred while update bills : {}", e.getMessage());
            return null;
        }
    }

    public void deleteBill(Long id) {
        try {
            billRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("An error occurred while Delete bills id {}: {}",id, e.getMessage());

        }

    }
}
