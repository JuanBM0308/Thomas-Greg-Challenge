package com.code.thomasgregback.service.role;

import com.code.thomasgregback.entity.Role;
import com.code.thomasgregback.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{
    protected RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) { this.roleRepository = roleRepository; }

    public Optional<Role> findById(Long id) {
        return this.roleRepository.findById(id);
    }

}
