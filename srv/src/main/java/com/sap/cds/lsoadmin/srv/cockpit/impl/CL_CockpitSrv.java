package com.sap.cds.lsoadmin.srv.cockpit.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sap.cds.lsoadmin.srv.cockpit.intf.IF_CockpitSrv;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.persistence.PersistenceService;

import cds.gen.db.esmlogs.Esmappmsglog;
import cds.gen.db.esmlogs.Esmappmsglog_;
import lombok.RequiredArgsConstructor;
 
@Service
@RequiredArgsConstructor
public class CL_CockpitSrv implements IF_CockpitSrv
{

    private final PersistenceService ps;

    @Override
    public List<Esmappmsglog> getCompleteLogHistory()
    {
       List<Esmappmsglog> logs = null;

        CqnSelect qLogsAll = Select.from(Esmappmsglog_.class);
        logs =  ps.run(qLogsAll).listOf(Esmappmsglog.class);
        return logs;
    }

}
