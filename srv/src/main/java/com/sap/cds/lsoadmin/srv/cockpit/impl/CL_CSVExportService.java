package com.sap.cds.lsoadmin.srv.cockpit.impl;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.sap.cds.lsoadmin.exceptions.EX_LSOADMIN;
import com.sap.cds.lsoadmin.srv.cockpit.intf.IF_CSVExportService;
import com.sap.cds.lsoadmin.srv.cockpit.pojos.TY_LogCLFDetails;

import cds.gen.db.esmlogs.Esmappmsglog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CL_CSVExportService implements IF_CSVExportService
{

    private final MessageSource msgSrc;

    @Override
    public <T> void generateCSV(Writer writer, List<T> items) throws EX_LSOADMIN
    {
        if (CollectionUtils.isNotEmpty(items))
        {
            Object o = items.get(0);
            if (o instanceof TY_LogCLFDetails)
            {
                List<Esmappmsglog> logDetails = (List<Esmappmsglog>) items;
                if (CollectionUtils.isNotEmpty(logDetails))
                {
                    try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT))
                    {
                        csvPrinter.printRecord("Message Type", "Status", "User Name", "Message", "Timestamp");
                        for (Esmappmsglog log : logDetails)
                        {
                            csvPrinter.printRecord(log.getMsgtype(), log.getStatus(), log.getUsername(),
                                    log.getMessage(), log.getTimestamp());
                        }
                    }
                    catch (IOException e)
                    {
                        log.error("Error While writing CSV ", e.getLocalizedMessage());
                        String msg = msgSrc.getMessage("ERR_CSV_DL_LOGS", new Object[]
                        { e.getLocalizedMessage() }, Locale.ENGLISH);
                        throw new EX_LSOADMIN(msg);
                    }
                }
            }
        }
    }

}
