package com.borzdykooa.callservice.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.borzdykooa.callservice.dto.CallDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CallControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void saveCall() throws Exception {
		CallDto callDto = CallDto.builder()
				.firstName("Olga")
				.lastName("Borzdyko")
				.phoneNumber("123456789")
				.build();
		String json = objectMapper.writeValueAsString(callDto);

		mockMvc.perform(post("/api/v1/call")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		File file = new File(String.join(File.separator, "calls", "Borzdyko_Olga.txt"));
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String line = bufferedReader.readLine();
		bufferedReader.close();

		assertEquals(" (00420 123 456 789)", line.substring(8, 28));
		assertTrue(file.delete());
	}

	@Test
	public void saveCall2() throws Exception {
		CallDto callDto = CallDto.builder()
				.lastName("Matveenko")
				.phoneNumber("+123 (45) 98-12-456")
				.build();
		String json = objectMapper.writeValueAsString(callDto);

		mockMvc.perform(post("/api/v1/call")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		File file = new File(String.join(File.separator, "calls", "Matveenko.txt"));
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String line = bufferedReader.readLine();
		bufferedReader.close();

		assertEquals(" (00123 459 812 456)", line.substring(8, 28));
		assertTrue(file.delete());
	}
}
