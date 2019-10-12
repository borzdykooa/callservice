package com.borzdykooa.callservice.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.borzdykooa.callservice.validation.PhoneNumberConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CallDto {
	@Size(min = 1, max = 30)
	private String firstName;

	@NotNull
	@Size(min = 1, max = 30)
	private String lastName;

	@JsonIgnore
	private LocalDateTime localDateTime = LocalDateTime.now();

	@PhoneNumberConstraint
	private String phoneNumber;
}
