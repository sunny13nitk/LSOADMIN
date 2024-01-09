package com.sap.cds.lsoadmin.srv.cockpit.pojos;

import java.util.List;

import cds.gen.db.esmlogs.Esmappmsglog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TY_LogCLFDetails
{

    private String msgName; // Enum Name
    private String stext; // Short Tect from Msessage Types Config
    private String lvl; // Message Level
    private long count;
    private List<Esmappmsglog> logs;

}
