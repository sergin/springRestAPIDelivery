package com.delivery.sergiolog.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class Problem {
	
	private Integer status;
	private LocalDateTime dateHour;
	private String title;
	private List<Countryside> Countryside;
	
	@AllArgsConstructor
	@Getter
	public static class Countryside{
		
		private String name;
		private String msg;
		
	}

}
