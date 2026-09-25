package com.ssiposs.pastebin.service;

import com.ssiposs.pastebin.model.PasteResponse;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class PasteService {

    private static final String ALPHABET =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 8;

    private final ConcurrentHashMap<String, String> codeToUrl = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, String> urlToCode = new ConcurrentHashMap<>();
    private final SecureRandom random = new SecureRandom();

    public PasteResponse create(String longUrl) {
        String existingCode = urlToCode.get(longUrl);
        if (existingCode != null) {
            return new PasteResponse(existingCode, "/" + existingCode, longUrl);
        }

        String code;
        do {
            code = randomCode();
        } while (codeToUrl.putIfAbsent(code, longUrl) != null);

        urlToCode.putIfAbsent(longUrl, code);
        return new PasteResponse(code, "/" + code, longUrl);
    }

    public Optional<String> resolve(String code) {
        return Optional.ofNullable(codeToUrl.get(code));
    }

    private String randomCode() {
        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            sb.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }
}
