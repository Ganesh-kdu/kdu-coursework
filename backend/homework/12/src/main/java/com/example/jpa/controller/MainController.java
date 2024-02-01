package com.example.jpa.controller;

import com.example.jpa.entity.*;
import com.example.jpa.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class MainController {
    TenantService tenantService;
    ShiftService shiftService;
    UserService userService;
    ShiftTypeService shiftTypeService;
    ShiftUserService shiftUserService;

    public MainController(TenantService tenantService, ShiftService shiftService, UserService userService, ShiftTypeService shiftTypeService, ShiftUserService shiftUserService) {
        this.tenantService = tenantService;
        this.shiftService = shiftService;
        this.userService = userService;
        this.shiftTypeService = shiftTypeService;
        this.shiftUserService = shiftUserService;
    }

    @GetMapping("/get/tenants")
    public ResponseEntity<List<Tenant>> getAllTenants() {
        List<Tenant> tenants = tenantService.getAllTenants();
        return ResponseEntity.ok(tenants);
    }

    @GetMapping("/get/users")
    public ResponseEntity<Page<User>> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size) {
        if (page < 0) {
            page = 0;
        }
        if (size < 1 || size > 50) {
            size = 50;
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<User> usersPage = userService.findAllUsers(pageable);
        return ResponseEntity.ok(usersPage);
    }

    @GetMapping("/get/top3shifts")
    public ResponseEntity<List<Shifts>> getTop3Shifts() {
        LocalDate dateStart = LocalDate.of(2023, 1, 1);
        LocalDate dateEnd = LocalDate.of(2023, 1, 27);
        List<Shifts> top3Shifts = shiftService.findTop3ShiftsByDateRange(dateStart, dateEnd);
        return ResponseEntity.ok(top3Shifts);
    }

    @PostMapping("/add/shifts")
    public ResponseEntity<String> saveShift(@RequestBody Shifts shift) {
        shiftService.saveShift(shift);
        return ResponseEntity.ok("Shift saved successfully");
    }

    @PostMapping("/add/tenant")
    public ResponseEntity<String> saveTenant(@RequestBody Tenant tenant) {
        tenantService.addTenant(tenant);
        return ResponseEntity.ok("Tenant saved successfully");
    }

    @PostMapping("/add/users")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok("User saved successfully");
    }

    @PostMapping("/add/shift-types")
    public ResponseEntity<String> saveShiftType(@RequestBody ShiftType shiftType) {
        shiftTypeService.saveShiftType(shiftType);
        return ResponseEntity.ok("ShiftType saved successfully");
    }

    @PostMapping("/add/shift-users")
    public ResponseEntity<String> saveShiftUser(@RequestBody ShiftUser shiftUser) {
        shiftUserService.saveShiftUser(shiftUser);
        return ResponseEntity.ok("ShiftUser saved successfully");
    }

    @DeleteMapping("/delete/shift-users/{userId}")
    public ResponseEntity<String> deleteShiftUser(@PathVariable UUID userId) {
        shiftUserService.deleteShiftUserByShiftEndsAt(userId);
        return ResponseEntity.ok("ShiftUser deleted successfully");
    }

    @PutMapping("/update/user/{userId}")
    public ResponseEntity<String> updateUserDetails(@PathVariable UUID userId, @RequestBody User user) {
        userService.updateUserDetails(userId, user);
        return ResponseEntity.ok("User details updated successfully");
    }
}
