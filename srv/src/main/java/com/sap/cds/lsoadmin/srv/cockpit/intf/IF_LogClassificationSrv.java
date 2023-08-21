package com.sap.cds.lsoadmin.srv.cockpit.intf;

import java.util.List;

import com.sap.cds.lsoadmin.exceptions.EX_LSOADMIN;
import com.sap.cds.lsoadmin.srv.cockpit.pojos.TY_LogsReport;

import cds.gen.db.esmlogs.Esmappmsglog;

public interface IF_LogClassificationSrv
{
    public TY_LogsReport classifyLogs4Reporting(List<Esmappmsglog> logs) throws EX_LSOADMIN;
}
