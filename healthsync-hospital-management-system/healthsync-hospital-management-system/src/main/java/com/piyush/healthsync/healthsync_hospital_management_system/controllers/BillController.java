package com.piyush.healthsync.healthsync_hospital_management_system.controllers;

import com.piyush.healthsync.healthsync_hospital_management_system.models.Bill;
import com.piyush.healthsync.healthsync_hospital_management_system.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping
    public Page<Bill> getAllBills(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int pageSize) {
        System.out.println("getAllBills");
        return billService.getAllBills(page, pageSize);
    }

    @GetMapping("/{id}")
    public Bill getBillById(@PathVariable Long id){
        System.out.println("getBillById");
        return billService.getBillById(id);
    }

    @PostMapping
    public Bill createBill(@RequestBody Bill bill){
        System.out.println("createBill");
        return billService.createBill(bill);
    }

    @PutMapping("/{id}")
    public Bill updateBill(@PathVariable Long id, @RequestBody Bill bill){
        System.out.println("updateBill");
        return billService.updateBill(id, bill);
    }

    @DeleteMapping("/{id}")
    public void deleteBill(@PathVariable Long id){
        System.out.println("deleteBill");
        billService.deleteBill(id);
    }
}
