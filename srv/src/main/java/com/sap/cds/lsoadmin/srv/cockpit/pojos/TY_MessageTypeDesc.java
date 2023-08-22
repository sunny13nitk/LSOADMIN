package com.sap.cds.lsoadmin.srv.cockpit.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByPosition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TY_MessageTypeDesc
{
    @CsvBindByPosition(position = 0)
    private String messageType;
    @CsvBindByPosition(position = 1)
    private String desc;
    @CsvBindByPosition(position = 2)
    private String descL;
    @CsvBindByPosition(position = 3)
    private String level;

}
