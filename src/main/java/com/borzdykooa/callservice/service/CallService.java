package com.borzdykooa.callservice.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.borzdykooa.callservice.dto.CallDto;
import com.borzdykooa.callservice.exception.ApplicationException;

@Service
public class CallService {

	public void saveCall(CallDto callDto) {
		String fileName = callDto.getFirstName() != null
				? callDto.getLastName() + "_" + callDto.getFirstName() + ".txt" : callDto.getLastName() + ".txt";
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
			phoneNumber = "00420" + phoneNumber;
		}

		return phoneNumber.replaceAll("(\\d{5})(\\d{3})(\\d{3})(\\d{3})", "($1 $2 $3 $4)");
	}
}
