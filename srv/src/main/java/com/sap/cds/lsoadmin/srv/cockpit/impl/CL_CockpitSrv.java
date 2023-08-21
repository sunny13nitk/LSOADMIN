package com.sap.cds.lsoadmin.srv.cockpit.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sap.cds.lsoadmin.enums.EnumDurations;
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
        logs = ps.run(qLogsAll).listOf(Esmappmsglog.class);
        return logs;
    }

    @Override
    public List<Esmappmsglog> getHistory4Duration(EnumDurations duration)
    {
        List<Esmappmsglog> logs = null;
        Calendar today = Calendar.getInstance();
        Date fromDate = null;

        if (duration != null)
        {
            switch (duration.name())
            {
            case "D1":
                today.add(Calendar.DAY_OF_YEAR, -1);
                fromDate = today.getTime();
                break;

            case "W1":
                today.add(Calendar.DAY_OF_YEAR, -7);
                fromDate = today.getTime();
                break;

            case "M1":
                today.add(Calendar.MONTH, -1);
                fromDate = today.getTime();
                break;

            case "M3":
                today.add(Calendar.MONTH, -3);
                fromDate = today.getTime();
                break;

            case "M6":
                today.add(Calendar.MONTH, -6);
                fromDate = today.getTime();
                break;

            case "Y1":
                today.add(Calendar.YEAR, -1);
                fromDate = today.getTime();
                break;

            case "All":
                return this.getCompleteLogHistory();

            default:
                return this.getCompleteLogHistory();
            }

            if (fromDate != null)
            {
                // Select Based on Date

                Date selDate = fromDate;
                CqnSelect qLogsAll = Select.from(Esmappmsglog_.class).where(q -> q.timestamp().ge(selDate.toInstant()));
                logs = ps.run(qLogsAll).listOf(Esmappmsglog.class);
            }

        }

        return logs;
    }

}
