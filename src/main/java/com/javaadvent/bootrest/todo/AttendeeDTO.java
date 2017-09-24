package com.javaadvent.bootrest.todo;

import org.hibernate.validator.constraints.NotEmpty;
public final class AttendeeDTO {

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@NotEmpty
    private String id;

    @NotEmpty
    private String name;
    
    @NotEmpty
    private String email;

    @NotEmpty
    private String orgName;

    //Constructor, getters, and setters are omitted
}
