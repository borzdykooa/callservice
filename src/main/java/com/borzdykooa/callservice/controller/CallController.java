package com.borzdykooa.callservice.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.borzdykooa.callservice.dto.CallDto;
import com.borzdykooa.callservice.service.CallService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CallController {

	private final CallService callService;

	@PostMapping("api/v1/call")
	public void saveCall(@RequestBody @Valid @NotNull CallDto callDto) {
		callService.saveCall(callDto);
	}
}
