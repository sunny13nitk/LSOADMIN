package com.sap.cds.lsoadmin.srv.cockpit.intf;

import java.util.List;

import com.sap.cds.lsoadmin.enums.EnumDurations;

import cds.gen.db.esmlogs.Esmappmsglog;

public interface IF_CockpitSrv
{
    public List<Esmappmsglog> getCompleteLogHistory();

    public List<Esmappmsglog> getHistory4Duration(EnumDurations duration);
}
