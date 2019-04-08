package com.dpc.dto;




import java.util.Date;

import lombok.Data;

@Data 

public class UserDto {

	private String username;
	private String password;
	private boolean enabled;
	private String profil;
	private String createdOn;

	
}
