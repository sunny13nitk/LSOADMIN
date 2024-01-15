package com.sap.cds.lsoadmin.srv.cockpit.intf;

import java.util.List;

import cds.gen.db.esmlogs.Esmappmsglog;

public interface IF_CSV_LoaderSrv
{
    public void loadLogs(List<Esmappmsglog> logs4Session);

    public List<Esmappmsglog> getLogs4mSession4CSVDL();
}
