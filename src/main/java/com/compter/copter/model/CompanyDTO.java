package com.compter.copter.model;

import jakarta.validation.constraints.Size;


public class CompanyDTO {

    private Long companyId;

    @Size(max = 255)
    private String companyName;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(final Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(final String companyName) {
        this.companyName = companyName;
    }

}
