package com.api.mscustomer.dto;

import java.util.List;

public class ApiResponseDTO {
    private List<AccountResponseDTO> data;

    public List<AccountResponseDTO> getData() {
        return data;
    }

    public void setData(List<AccountResponseDTO> data) {
        this.data = data;
    }
}
