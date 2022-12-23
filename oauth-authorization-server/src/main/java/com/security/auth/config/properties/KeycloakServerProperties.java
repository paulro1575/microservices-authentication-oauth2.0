package com.security.auth.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "keycloak.server")
public class KeycloakServerProperties {

    private String contextPath;
    private String realmImportFile;
    private String customRealm;
    private AdminUser adminUser = new AdminUser();
    private AdminUser customUser= new AdminUser();

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getRealmImportFile() {
        return realmImportFile;
    }

    public void setRealmImportFile(String realmImportFile) {
        this.realmImportFile = realmImportFile;
    }

    public String getCustomRealm() {
        return customRealm;
    }

    public void setCustomRealm(String customRealm) {
        this.customRealm = customRealm;
    }

    public AdminUser getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

    public AdminUser getCustomUser() {
        return customUser;
    }

    public void setCustomUser(AdminUser customUser) {
        this.customUser = customUser;
    }
}
