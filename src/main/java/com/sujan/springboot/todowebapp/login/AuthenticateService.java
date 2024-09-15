package com.sujan.springboot.todowebapp.login;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticateService {

    @Value("${csv.file.path}")
    private String csvFilePath;

    private Map<String, String> userCredentials = new HashMap<>();

    @PostConstruct
    public void init() {
        try {
            loadUsers();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load users from CSV", e);
        }
    }

    private void loadUsers() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            // Skip header
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 2) {
                    String username = fields[0].trim();
                    String password = fields[1].trim();
                    userCredentials.put(username, password);
                }
            }
        }
    }

    public boolean authenticate(String username, String password) {
        String storedPassword = userCredentials.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }
}
