package com.sap.cds.lsoadmin.srv.cockpit.intf;

import java.io.Writer;
import java.util.List;

import com.sap.cds.lsoadmin.exceptions.EX_LSOADMIN;

public interface IF_CSVExportService
{
    public <T> void generateCSV(Writer writer, List<T> items) throws EX_LSOADMIN;
}
