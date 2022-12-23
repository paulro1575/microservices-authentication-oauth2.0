package com.security.auth.config;

import java.util.NoSuchElementException;

import com.security.auth.config.properties.AdminUser;
import com.security.auth.config.properties.KeycloakServerProperties;
import org.keycloak.Config;
import org.keycloak.exportimport.ExportImportManager;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.RoleModel;
import org.keycloak.models.UserCredentialModel;
import org.keycloak.models.UserModel;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.services.managers.ApplianceBootstrap;
import org.keycloak.services.managers.RealmManager;
import org.keycloak.services.resources.KeycloakApplication;
import org.keycloak.services.util.JsonConfigProviderFactory;
import org.keycloak.util.JsonSerialization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class EmbeddedKeycloakApplication extends KeycloakApplication {

	private static final Logger LOG = LoggerFactory.getLogger(EmbeddedKeycloakApplication.class);
	static KeycloakServerProperties keycloakServerProperties;
	
	protected void loadConfig() {
        final JsonConfigProviderFactory factory = new RegularJsonConfigProviderFactory();
        Config.init(factory.create().orElseThrow(() -> new NoSuchElementException("No value present")));
    }

	@Override
	protected ExportImportManager bootstrap() {
		final ExportImportManager exportImportManager = super.bootstrap();
		createMasterRealmAdminUser();
		createSecurityRealm();
		return exportImportManager;
	}

	private void createMasterRealmAdminUser() {
		final KeycloakSession session = getSessionFactory().create();
		final ApplianceBootstrap applianceBootstrap = new ApplianceBootstrap(session);
		final AdminUser admin = keycloakServerProperties.getAdminUser();

		try {
			session.getTransactionManager().begin();
			applianceBootstrap.createMasterRealmUser(admin.getUsername(), admin.getPassword());
			session.getTransactionManager().commit();
			LOG.info("User for master realm was created successfully: " + admin.getUsername());
		} catch (Exception ex) {
			LOG.warn("Couldn't create keycloak master admin user: {}", ex.getMessage());
			session.getTransactionManager().rollback();
		}
		session.close();
	}

	private void createSecurityRealm() {
		final KeycloakSession session = getSessionFactory().create();
		final Resource lessonRealmImportFile = new ClassPathResource(keycloakServerProperties.getRealmImportFile());
		try {
			session.getTransactionManager().begin();
			final RealmManager manager = new RealmManager(session);
			final RealmRepresentation realmRepresentation = JsonSerialization.readValue(
					lessonRealmImportFile.getInputStream(),
					RealmRepresentation.class
			);

			manager.importRealm(realmRepresentation);
			session.getTransactionManager().commit();

			LOG.info("Realm json file was imported successfully: " + lessonRealmImportFile.getFile().getAbsolutePath());
		} catch (Exception ex) {
			LOG.error("Failed to import Realm json file: {}", ex.getMessage(), ex);
			session.getTransactionManager().rollback();
		}

		session.close();
		createSecurityRealmAdminUser(keycloakServerProperties.getCustomRealm());
	}

	private AdminUser createSecurityUser(final KeycloakSession session, final RealmModel realm) {
		LOG.info("Realm to use: " + realm.getDisplayName());
		final AdminUser admin = keycloakServerProperties.getCustomUser();
		final UserModel adminUser = session.users().addUser(realm, admin.getUsername());
		adminUser.setEnabled(true);
		final UserCredentialModel usrCredModel = UserCredentialModel.password(admin.getPassword());
		session.userCredentialManager().updateCredential(realm, adminUser, usrCredModel);
		final RoleModel adminRole = realm.getRole("admin_role");
		adminUser.grantRole(adminRole);
		return admin;
	}

	private void createSecurityRealmAdminUser(final String realmId) {
		final KeycloakSession session = getSessionFactory().create();
		try {
			session.getTransactionManager().begin();
			final RealmManager manager = new RealmManager(session);
			final RealmModel realm = manager.getRealm(realmId);
			final AdminUser adminUser = createSecurityUser(session, realm);
			session.getTransactionManager().commit();
			LOG.info("User for security realm was created successfully: " + adminUser.getUsername());
		} catch (Exception ex) {
			LOG.warn("Couldn't create keycloak security admin user: {}", ex.getMessage());
			session.getTransactionManager().rollback();
		}
		session.close();
	}
}
