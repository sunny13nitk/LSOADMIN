package com.sap.cds.lsoadmin.srv.cockpit.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.sap.cds.lsoadmin.srv.cockpit.intf.IF_CSV_LoaderSrv;

import cds.gen.db.esmlogs.Esmappmsglog;

@Service
@SessionScope
public class CL_CSV_LoaderSrv implements IF_CSV_LoaderSrv
{
    private List<Esmappmsglog> sessLogs;

    @Override
    public void loadLogs(List<Esmappmsglog> logs4Session)
    {
        this.sessLogs.clear();
        this.sessLogs = logs4Session;
    }

    @Override
    public List<Esmappmsglog> getLogs4mSession4CSVDL()
    {
        return sessLogs;
    }

}
