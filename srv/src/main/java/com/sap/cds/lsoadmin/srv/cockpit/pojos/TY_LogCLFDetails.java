package com.sap.cds.lsoadmin.srv.cockpit.pojos;

import java.util.List;

import com.sap.cds.lsoadmin.enums.EnumMessageType;

import cds.gen.db.esmlogs.Esmappmsglog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TY_LogCLFDetails
{
    private EnumMessageType msgType;
    private String msgName; // Enum Name
    private String stext; // Short Tect from Msessage Types Config
    private long count;
    private List<Esmappmsglog> logs;

}
