package com.cap10mycap10.userservice.dto;

import javax.validation.constraints.Size;

public class UserCreationDto {

    @Size(min = 1, message = "{Size.userDto.firstName}")
    private String firstName;

    @Size(min = 1, message = "{Size.userDto.lastName}")
    private String lastName;

    @Size(min = 5, max = 50)
    private String username;

    @Size(min = 1, message = "{Size.userDto.email}")
    private String email;

    private Long agentId;

    private Long clientId;

    private Long roleId;


    //The constructor that takes a builder from which it will create object
    //the access to this is only provided to builder
    private UserCreationDto(UserCreationDtoBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.username = builder.username;
        this.email = builder.email;
        this.agentId = builder.agentId;
        this.clientId = builder.clientId;
        this.roleId = builder.roleId;
    }

    public static class UserCreationDtoBuilder {
        @Size(min = 1, message = "{Size.userDto.firstName}")
        private String firstName;

        @Size(min = 1, message = "{Size.userDto.lastName}")
        private String lastName;

        @Size(min = 5, max = 50)
        private String username;

        @Size(min = 1, message = "{Size.userDto.email}")
        private String email;

        private Long agentId;

        private Long clientId;

        private Long roleId;

        //All Mandatory parameters goes with this constructor
        public UserCreationDtoBuilder(String firstName,
                                      String lastName,
                                      String username,
                                      String email,
                                      Long roleId) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.username =username;
            this.email = email;
            this.roleId = roleId;
        }

        //setters for optional parameters which returns this same builder
        //to support fluent design
        public UserCreationDtoBuilder withAgentId(Long  agentId) {
            this.agentId = agentId;
            return this;
        }

        public UserCreationDtoBuilder withClientId(Long  clientId) {
            this.clientId = clientId;
            return this;
        }



        //the actual build method that prepares and returns a BankAccount object
        public UserCreationDto build() {
            return new UserCreationDto(this);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Long getAgentId() {
        return agentId;
    }

    public Long getClientId() {
        return clientId;
    }


    public Long getRoleId() {
        return roleId;
    }
}