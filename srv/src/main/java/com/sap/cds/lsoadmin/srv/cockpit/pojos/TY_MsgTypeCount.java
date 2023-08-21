package com.sap.cds.lsoadmin.srv.cockpit.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TY_MsgTypeCount
{
    private String msgType;
    private long count;
}
