/**
 * Copyright 2018 onwards - Sunit Katkar (sunitkatkar@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sunitkatkar.blogspot.tenant.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class CustomUserDetails
        extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;

    private String tenant;

    public CustomUserDetails(String username, String password,
            Collection<? extends GrantedAuthority> authorities, String tenant) {
        super(username, password, authorities);
        this.tenant = tenant;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

}
