package com.sap.cds.lsoadmin.UIControllers;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sap.cds.lsoadmin.enums.EnumDurations;
import com.sap.cds.lsoadmin.srv.cockpit.intf.IF_CockpitSrv;
import com.sap.cds.lsoadmin.srv.cockpit.intf.IF_LogClassificationSrv;
import com.sap.cds.lsoadmin.srv.cockpit.pojos.TY_LogsReport;
import com.sap.cds.lsoadmin.srv.cockpit.pojos.TY_MessagesTypeDesc;

import cds.gen.db.esmlogs.Esmappmsglog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/cockpit")
@Slf4j
@RequiredArgsConstructor
public class CockpitController
{

    private final IF_CockpitSrv cpSrv;

    private final MessageSource msgSrc;

    private final IF_LogClassificationSrv logCFSrv;

    private final TY_MessagesTypeDesc msgsInfo;

    private final String logViewName = "logView";
    private final String errView = "error";

    @GetMapping("/")
    public String getDefaultLogHistory(Model model)
    {
        // Get Log history for Default :Last 1 Month
        if (cpSrv != null && logCFSrv != null)
        {
            List<Esmappmsglog> logs = cpSrv.getHistory4Duration(EnumDurations.M1);
            if (CollectionUtils.isNotEmpty(logs))
            {
                log.info("Logs Bound : " + logs.size());
                TY_LogsReport logsReport = logCFSrv.classifyLogs4Reporting(logs);
                if (CollectionUtils.isNotEmpty(logsReport.getChartData()))
                {
                    // Populate Model
                    model.addAttribute("logs", logs);
                    model.addAttribute("chartData", logsReport.getChartData());
                    model.addAttribute("logsCFTable", logsReport.getLogsTableData());
                    model.addAttribute("duration", EnumDurations.M1.name());
                    model.addAttribute("msgInfo", msgsInfo.getMsgTypesConfig());
                }

            }

        }
        return logViewName;
    }

    @GetMapping("/logs/{duration}")
    public String getCompleteLogHistory(@PathVariable String duration, Model model)
    {
        if (StringUtils.hasText(duration) && cpSrv != null)
        {
            // Get the Respective Enum
            Optional<EnumDurations> durEnumO = Arrays.stream(EnumDurations.values())
                    .filter(e -> e.name().equalsIgnoreCase(duration)).findFirst();
            if (durEnumO.isPresent())
            {
                List<Esmappmsglog> logs = cpSrv.getHistory4Duration(durEnumO.get());
                if (CollectionUtils.isNotEmpty(logs))
                {
                    log.info("Logs Bound : " + logs.size());
                    TY_LogsReport logsReport = logCFSrv.classifyLogs4Reporting(logs);
                    if (CollectionUtils.isNotEmpty(logsReport.getChartData()))
                    {
                        // Populate Model
                        model.addAttribute("logs", logs);
                        model.addAttribute("chartData", logsReport.getChartData());
                        model.addAttribute("logsCFTable", logsReport.getLogsTableData());
                        model.addAttribute("duration", durEnumO.get().name());
                        model.addAttribute("msgInfo", msgsInfo.getMsgTypesConfig());
                    }
                }

            }
            else
            {
                if (msgSrc != null)
                {
                    String msg = msgSrc.getMessage("ERR_DURATION", new Object[]
                    { duration }, Locale.ENGLISH);
                    model.addAttribute("formError", msg);
                    return errView;
                }
            }

        }
        return logViewName;
    }

}
