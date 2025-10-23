package com.example.authservicev2.controller;

import com.example.authservicev2.domain.model.request.AddRoleRequest;
import com.example.authservicev2.domain.model.request.DeleteRoleRequest;
import com.example.authservicev2.domain.model.request.RolePermissionRequest;
import com.example.authservicev2.domain.model.response.RolePermissionResponse;
import com.example.authservicev2.service.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/role_v1")
@RequiredArgsConstructor
public class RoleController {


    private final RoleService roleService;

    // Option 1: Trả về List<Permissions> đơn giản
    @PostMapping("/permissions")
    public ResponseEntity<RolePermissionResponse> getRolePermissions(
            @RequestBody RolePermissionRequest request) {
        // Lấy thông tin role từ request
        String roleName = request.getRole().getName();
        RolePermissionResponse response = roleService.getPermissionsByRoleName(roleName);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add-role")
    public ResponseEntity<String> addRole(@RequestBody AddRoleRequest request) {
        roleService.addRole(request);
        return ResponseEntity.ok("Role added thanh cong");
    }

    @PostMapping("/delete-role")
    public ResponseEntity<String> deleteRole(@RequestBody DeleteRoleRequest request) {
        roleService.deleteRole(request);
        return ResponseEntity.ok("Role xoa thanh cong");
    }
}
