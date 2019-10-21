package com.borzdykooa.callservice.service;

import com.borzdykooa.callservice.dto.CallDto;
import com.borzdykooa.callservice.exception.ApplicationException;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@Service
public class CallService {

    private static final String DEFAULT_CODE_PART = "00420";
    private static final String PHONE_NUMBER_REGEXP = "(\\d{5})(\\d{3})(\\d{3})(\\d{3})";

    public void saveCall(CallDto callDto) {
        String fileName = String.format("%s.txt", callDto.getFirstName() != null
                ? callDto.getLastName() + "_" + callDto.getFirstName() : callDto.getLastName());
        File file = new File(String.join(File.separator, "calls", fileName));
        try {
            file.createNewFile();
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
                bufferedWriter.append(String.valueOf(callDto.getLocalDateTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"))))
                        .append(" ")
                        .append(normalizePhoneNumber(callDto.getPhoneNumber()))
                        .append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new ApplicationException("IOException occurred in CallService:saveCall");
        }
    }

    private String normalizePhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber.replace("+", "00");
        phoneNumber = phoneNumber.replaceAll("[\\-() ]", "");
        if (phoneNumber.length() == 9) {
            phoneNumber = DEFAULT_CODE_PART + phoneNumber;
        }

        return phoneNumber.replaceAll(PHONE_NUMBER_REGEXP, "($1 $2 $3 $4)");
    }
}
