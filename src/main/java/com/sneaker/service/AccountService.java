package com.sneaker.service;

import com.sneaker.dto.request.*;
import com.sneaker.entity.Account;
import com.sneaker.entity.AccountAddress;
import com.sneaker.repository.AccountAddressRepository;
import com.sneaker.repository.AccountRepository;
import com.sneaker.security.SecurityUser;
import com.sneaker.util.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    
    private final AccountRepository accountRepository;
    private final AccountAddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;
    private final CodeGenerator codeGenerator;
    
    public Page<Account> getAllAccounts(Account.Role role, Account.AccountStatus status, 
                                       String search, Pageable pageable) {
        return accountRepository.findWithFilters(role, status, search, pageable);
    }
    
    public Account getAccountById(Integer id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản"));
    }
    
    @Transactional
    public Account createAccount(RegisterRequest request) {
        if (accountRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email đã được sử dụng");
        }
        
        if (request.getPhoneNumber() != null && 
            accountRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new RuntimeException("Số điện thoại đã được sử dụng");
        }
        
        Account account = new Account();
        account.setFullName(request.getFullName());
        account.setEmail(request.getEmail());
        account.setPhoneNumber(request.getPhoneNumber());
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account.setRole(Account.Role.CUSTOMER);
        account.setStatus(Account.AccountStatus.HOAT_DONG);
        account.setCode(codeGenerator.generateAccountCode(Account.Role.CUSTOMER));
        
        return accountRepository.save(account);
    }
    
    @Transactional
    public Account updateAccount(Integer id, AccountUpdateRequest request) {
        Account account = getAccountById(id);
        
        if (request.getEmail() != null && !request.getEmail().equals(account.getEmail())) {
            if (accountRepository.existsByEmail(request.getEmail())) {
                throw new RuntimeException("Email đã được sử dụng");
            }
            account.setEmail(request.getEmail());
        }
        
        if (request.getPhoneNumber() != null && 
            !request.getPhoneNumber().equals(account.getPhoneNumber())) {
            if (accountRepository.existsByPhoneNumber(request.getPhoneNumber())) {
                throw new RuntimeException("Số điện thoại đã được sử dụng");
            }
            account.setPhoneNumber(request.getPhoneNumber());
        }
        
        if (request.getFullName() != null) account.setFullName(request.getFullName());
        if (request.getGender() != null) account.setGender(request.getGender());
        if (request.getBirthday() != null) account.setBirthday(request.getBirthday());
        if (request.getCitizenId() != null) account.setCitizenId(request.getCitizenId());
        if (request.getAvatar() != null) account.setAvatar(request.getAvatar());
        if (request.getStatus() != null) {
            account.setStatus(Account.AccountStatus.valueOf(request.getStatus()));
        }
        
        return accountRepository.save(account);
    }
    
    @Transactional
    public Account updateAccountStatus(Integer id, AccountStatusUpdateRequest request) {
        Account account = getAccountById(id);
        
        try {
            Account.AccountStatus status = Account.AccountStatus.valueOf(request.getStatus());
            account.setStatus(status);
            return accountRepository.save(account);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Trạng thái không hợp lệ");
        }
    }
    
    @Transactional
    public void deleteAccount(Integer id) {
        Account account = getAccountById(id);
        accountRepository.delete(account);
    }
    
    public Account getCurrentAccount() {
        SecurityUser userDetails = (SecurityUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Integer accountId = userDetails.getAccount().getId();
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản"));
    }
    
    @Transactional
    public Account updateProfile(ProfileUpdateRequest request) {
        Account account = getCurrentAccount();
        
        if (request.getFullName() != null) account.setFullName(request.getFullName());
        if (request.getPhoneNumber() != null) {
            if (!request.getPhoneNumber().equals(account.getPhoneNumber()) &&
                accountRepository.existsByPhoneNumber(request.getPhoneNumber())) {
                throw new RuntimeException("Số điện thoại đã được sử dụng");
            }
            account.setPhoneNumber(request.getPhoneNumber());
        }
        if (request.getBirthday() != null) account.setBirthday(request.getBirthday());
        if (request.getGender() != null) account.setGender(request.getGender());
        if (request.getCitizenId() != null) account.setCitizenId(request.getCitizenId());
        if (request.getAvatar() != null) account.setAvatar(request.getAvatar());
        
        return accountRepository.save(account);
    }
    
    @Transactional
    public void changePassword(ChangePasswordRequest request) {
        Account account = getCurrentAccount();
        
        if (!passwordEncoder.matches(request.getCurrentPassword(), account.getPassword())) {
            throw new RuntimeException("Mật khẩu hiện tại không chính xác");
        }
        
        account.setPassword(passwordEncoder.encode(request.getNewPassword()));
        accountRepository.save(account);
    }
    
    @Transactional
    public AccountAddress addAddress(AddressRequest request) {
        Account account = getCurrentAccount();
        
        AccountAddress address = new AccountAddress();
        address.setAccount(account);
        address.setName(request.getName());
        address.setPhoneNumber(request.getPhoneNumber());
        address.setProvinceId(request.getProvinceId());
        address.setDistrictId(request.getDistrictId());
        address.setWardId(request.getWardId());
        address.setSpecificAddress(request.getSpecificAddress());
        address.setType(request.getType() != null ? request.getType() : false);
        address.setIsDefault(request.getIsDefault() != null ? request.getIsDefault() : false);
        
        if (address.getIsDefault()) {
            // Remove default from other addresses
            List<AccountAddress> addresses = addressRepository.findByAccountId(account.getId());
            addresses.forEach(addr -> addr.setIsDefault(false));
            addressRepository.saveAll(addresses);
        }
        
        return addressRepository.save(address);
    }
    
    @Transactional
    public AccountAddress updateAddress(Integer addressId, AddressRequest request) {
        Account account = getCurrentAccount();
        AccountAddress address = addressRepository.findByIdAndAccountId(addressId, account.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy địa chỉ"));
        
        if (request.getName() != null) address.setName(request.getName());
        if (request.getPhoneNumber() != null) address.setPhoneNumber(request.getPhoneNumber());
        if (request.getProvinceId() != null) address.setProvinceId(request.getProvinceId());
        if (request.getDistrictId() != null) address.setDistrictId(request.getDistrictId());
        if (request.getWardId() != null) address.setWardId(request.getWardId());
        if (request.getSpecificAddress() != null) address.setSpecificAddress(request.getSpecificAddress());
        if (request.getType() != null) address.setType(request.getType());
        if (request.getIsDefault() != null) {
            if (request.getIsDefault()) {
                // Remove default from other addresses
                List<AccountAddress> addresses = addressRepository.findByAccountId(account.getId());
                addresses.forEach(addr -> addr.setIsDefault(false));
                addressRepository.saveAll(addresses);
            }
            address.setIsDefault(request.getIsDefault());
        }
        
        return addressRepository.save(address);
    }
    
    @Transactional
    public void deleteAddress(Integer addressId) {
        Account account = getCurrentAccount();
        addressRepository.deleteByIdAndAccountId(addressId, account.getId());
    }
    
    @Transactional
    public AccountAddress setDefaultAddress(Integer addressId) {
        Account account = getCurrentAccount();
        AccountAddress address = addressRepository.findByIdAndAccountId(addressId, account.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy địa chỉ"));
        
        // Remove default from other addresses
        List<AccountAddress> addresses = addressRepository.findByAccountId(account.getId());
        addresses.forEach(addr -> addr.setIsDefault(false));
        addressRepository.saveAll(addresses);
        
        address.setIsDefault(true);
        return addressRepository.save(address);
    }
}

