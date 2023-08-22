package com.sap.cds.lsoadmin.srv.cockpit.pojos;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TY_LogsReport
{
    private List<TY_MsgTypeCount> chartData = new ArrayList<TY_MsgTypeCount>();
    private List<TY_LogCLFDetails> logsTableData = new ArrayList<TY_LogCLFDetails>();
}
