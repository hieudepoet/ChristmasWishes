package com.project.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class GoogleSheetsConfig {
    private static final Dotenv dotenv = Dotenv.configure().load();
    private static final String CREDENTIALS_FILE_PATH = dotenv.get("GOOGLE_CREDENTIALS_PATH");

    @Bean(name = "googleSheetsConfigBean")
    public Sheets googleSheetsConfig() throws IOException, GeneralSecurityException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(CREDENTIALS_FILE_PATH))
                .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS));

        return new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                new HttpCredentialsAdapter(credentials)
        )
        .setApplicationName("Christmas Wishes")
        .build();
    }
}
