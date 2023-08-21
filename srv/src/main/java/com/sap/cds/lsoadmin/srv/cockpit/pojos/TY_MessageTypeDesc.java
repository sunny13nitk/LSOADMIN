package com.sap.cds.lsoadmin.srv.cockpit.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TY_MessageTypeDesc
{
    @JsonProperty("msgType")
    private String messageType;
    @JsonProperty("desc")
    private String desc;
    @JsonProperty("descL")
    private String descL;
    @JsonProperty("lvl")
    private String level;

}
