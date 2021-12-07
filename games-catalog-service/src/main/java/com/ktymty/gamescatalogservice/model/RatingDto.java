package com.ktymty.gamescatalogservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@JacksonStdImpl
@Builder
@Data
public class RatingDto {

    @JsonProperty(value = "Source")
    private String source;
    @JsonProperty(value = "Value")
    private String value;

}
