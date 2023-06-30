package com.example.cbd.runner;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class KeycloakInitializerRunner implements CommandLineRunner {

    @Value("${http://localhost:8080}")
    private String keycloakServerUrl;

    @Value("${art-shop}")
    private String keycloakRealm;

    @Value("${art-shop-app}")
    private String keycloakClientId;

    private static final String DEFAULT_CLIENT_ROLE = "user";

    @Override
    public void run(String... args) {
        log.info("Initializing '{}' realm in Keycloak ...", keycloakRealm);

        Keycloak keycloakAdmin = KeycloakBuilder.builder()
                .serverUrl(keycloakServerUrl)
                .realm("master")
                .clientId("admin-cli")
                .username("admin")
                .password("admin")
                .build();

        Optional<RealmRepresentation> representationOptional = keycloakAdmin.realms()
                .findAll()
                .stream()
                .filter(r -> r.getRealm().equals(keycloakRealm))
                .findAny();
        if (representationOptional.isPresent()) {
            log.info("Removing already pre-configured '{}' realm", keycloakRealm);
            keycloakAdmin.realm(keycloakRealm).remove();
        }

        // Realm
        RealmRepresentation realmRepresentation = new RealmRepresentation();
        realmRepresentation.setRealm(keycloakRealm);
        realmRepresentation.setEnabled(true);
        realmRepresentation.setRegistrationAllowed(true);

        // Client
        ClientRepresentation clientRepresentation = new ClientRepresentation();
        clientRepresentation.setClientId(keycloakClientId);
        clientRepresentation.setDirectAccessGrantsEnabled(true);
        clientRepresentation.setPublicClient(true);
        clientRepresentation.setRedirectUris(Collections.singletonList("http://localhost:3000/*"));
        clientRepresentation.setDefaultRoles(new String[]{DEFAULT_CLIENT_ROLE}); // Convert List to String[]
        realmRepresentation.setClients(Collections.singletonList(clientRepresentation));

        // Users
        List<UserRepresentation> userRepresentations = Arrays.asList(
                createUserRepresentation("admin", "admin", Collections.singletonList("admin")),
                createUserRepresentation("user", "user", Collections.singletonList(DEFAULT_CLIENT_ROLE))
        );
        realmRepresentation.setUsers(userRepresentations);

        // Create Realm
        keycloakAdmin.realms().create(realmRepresentation);

        // Testing
        UserRepresentation admin = userRepresentations.get(0);
        log.info("Testing getting token for '{}' ...", admin.getUsername());

        Keycloak keycloakMovieApp = KeycloakBuilder.builder()
                .serverUrl(keycloakServerUrl)
                .realm(keycloakRealm)
                .username(admin.getUsername())
                .password("admin") // Replace with the actual password for the admin user
                .clientId(keycloakClientId)
                .build();

        log.info("'{}' token: {}", admin.getUsername(), keycloakMovieApp.tokenManager().grantToken().getToken());
        log.info("'{}' initialization completed successfully!", keycloakRealm);
    }

    private UserRepresentation createUserRepresentation(String username, String password, List<String> roles) {
        // User Credentials
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(password);

        // User
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(username);
        userRepresentation.setEnabled(true);
        userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));
        userRepresentation.setClientRoles(Collections.singletonMap(keycloakClientId, roles));

        return userRepresentation;
    }
}