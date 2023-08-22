package com.sap.cds.lsoadmin.srv.cockpit.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.sap.cds.lsoadmin.exceptions.EX_LSOADMIN;
import com.sap.cds.lsoadmin.srv.cockpit.intf.IF_LogClassificationSrv;
import com.sap.cds.lsoadmin.srv.cockpit.pojos.TY_LogCLFDetails;
import com.sap.cds.lsoadmin.srv.cockpit.pojos.TY_LogsReport;
import com.sap.cds.lsoadmin.srv.cockpit.pojos.TY_MessageTypeDesc;
import com.sap.cds.lsoadmin.srv.cockpit.pojos.TY_MessagesTypeDesc;
import com.sap.cds.lsoadmin.srv.cockpit.pojos.TY_MsgTypeCount;

import cds.gen.db.esmlogs.Esmappmsglog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CL_LogClassificationSrv implements IF_LogClassificationSrv
{

    private final TY_MessagesTypeDesc msgTypesConfig;

    @Override
    public TY_LogsReport classifyLogs4Reporting(List<Esmappmsglog> logs) throws EX_LSOADMIN
    {
        TY_LogsReport logsClassF = null;

        if (CollectionUtils.isNotEmpty(logs) && CollectionUtils.isNotEmpty(msgTypesConfig.getMsgTypesConfig()))
        {
            // Grouping and Showing Group Key and Correspoding Entities in each Group
            Map<String, List<Esmappmsglog>> logsByMsgTypes = logs.stream()
                    .collect(Collectors.groupingBy(Esmappmsglog::getMsgtype));
            if (logsByMsgTypes.size() > 0)
            {
                logsClassF = new TY_LogsReport();
                // Prepare Chart Data
                for (Map.Entry<String, List<Esmappmsglog>> logByMsgType : logsByMsgTypes.entrySet())
                {
                    logsClassF.getChartData()
                            .add(new TY_MsgTypeCount(logByMsgType.getKey(), logByMsgType.getValue().size()));
                    log.info(logByMsgType.getKey() + " :" + logByMsgType.getValue().size());

                    // Get SText From Config
                    Optional<TY_MessageTypeDesc> msgTypeCfgO = msgTypesConfig.getMsgTypesConfig().stream()
                            .filter(f -> f.getMessageType().equals(logByMsgType.getKey())).findFirst();
                    if (msgTypeCfgO.isPresent())
                    {
                        logsClassF.getLogsTableData().add(new TY_LogCLFDetails(logByMsgType.getKey(),
                                msgTypeCfgO.get().getDesc(), logByMsgType.getValue().size(), logByMsgType.getValue()));
                        log.info(logByMsgType.getKey() + " : " + " : " + msgTypeCfgO.get().getDesc() + " : "
                                + logByMsgType.getValue().size() + " : " + logByMsgType.getValue());
                    }

                }

            }

        }

        return logsClassF;
    }

}
