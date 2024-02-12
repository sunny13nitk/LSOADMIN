package com.sap.cds.lsoadmin.srv.cockpit.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.sap.cds.lsoadmin.srv.cockpit.intf.IF_CSV_LoaderSrv;

import cds.gen.db.esmlogs.Esmappmsglog;
import lombok.extern.slf4j.Slf4j;

@Service
@SessionScope
@Slf4j
public class CL_CSV_LoaderSrv implements IF_CSV_LoaderSrv
{
    private List<Esmappmsglog> sessLogs = new ArrayList<Esmappmsglog>();

    @Override
    public void loadLogs(List<Esmappmsglog> logs4Session)
    {
        this.sessLogs.clear();
        this.sessLogs = logs4Session;
        if (CollectionUtils.isNotEmpty(sessLogs))
        {
            log.info("CSV Log Loader loaded logs #: " + sessLogs.size());
        }
    }

    @Override
    public List<Esmappmsglog> getLogs4mSession4CSVDL()
    {
        return this.sessLogs;
    }

}
