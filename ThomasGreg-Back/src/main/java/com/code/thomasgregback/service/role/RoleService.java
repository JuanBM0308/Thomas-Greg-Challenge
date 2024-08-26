package com.code.thomasgregback.service.role;

import com.code.thomasgregback.entity.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findById(Long id);
}
