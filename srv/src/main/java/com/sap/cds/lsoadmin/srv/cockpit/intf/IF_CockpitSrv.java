package com.sap.cds.lsoadmin.srv.cockpit.intf;

import java.util.List;

import cds.gen.db.esmlogs.Esmappmsglog;

public interface IF_CockpitSrv
{
    public List<Esmappmsglog> getCompleteLogHistory();
}
