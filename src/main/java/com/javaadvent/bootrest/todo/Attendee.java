package com.javaadvent.bootrest.todo;

import org.springframework.data.annotation.Id;

public final class Attendee {

    @Id
    private String id;

    private String name;

    private String email;
    
    private String orgName;

    public Attendee() {}

    private Attendee(Builder builder) {
    	this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.orgName = builder.orgName;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    //Other getters are omitted

    public void update(String id, String name, String email, String orgName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.orgName = orgName;
    }

    /**
     * We don't have to use the builder pattern here because the constructed 
  * class has only two String fields. However, I use the builder pattern 
  * in this example because it makes the code a bit easier to read.
     */
    public static class Builder {

    	private String id;

        private String name;

        private String email;
        
        private String orgName;

        private Builder() {}

        public Builder id(String id) {
            this.id = id;
            return this;
        }
        
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        
        public Builder orgName(String orgName) {
            this.orgName = orgName;
            return this;
        }     

		public Attendee build() {
        	Attendee build = new Attendee(this);
            return build;
        }
    }

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
}
