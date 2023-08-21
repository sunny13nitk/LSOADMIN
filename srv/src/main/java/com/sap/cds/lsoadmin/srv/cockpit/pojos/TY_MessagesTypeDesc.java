package com.sap.cds.lsoadmin.srv.cockpit.pojos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TY_MessagesTypeDesc
{
    private List<TY_MessageTypeDesc> msgTypesConfig;
}
