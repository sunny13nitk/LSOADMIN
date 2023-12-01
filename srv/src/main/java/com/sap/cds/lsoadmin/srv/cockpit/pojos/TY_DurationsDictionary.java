package com.sap.cds.lsoadmin.srv.cockpit.pojos;

import java.util.HashMap;
import java.util.Map;

import com.sap.cds.lsoadmin.enums.EnumDurations;

public class TY_DurationsDictionary
{
    public static Map<String, String> getDurationsDescriptions()
    {
        Map<String, String> durationsMap = new HashMap<String, String>();

        durationsMap.put(EnumDurations.D1.name(), "Today");
        durationsMap.put(EnumDurations.W1.name(), "Last Week");
        durationsMap.put(EnumDurations.M1.name(), "Last Month");
        durationsMap.put(EnumDurations.M3.name(), "Last 3 Month(s)");
        durationsMap.put(EnumDurations.M6.name(), "Last 6 Month(s)");
        durationsMap.put(EnumDurations.Y1.name(), "Last Year");
        durationsMap.put(EnumDurations.All.name(), "All");

        return durationsMap;

    }
}
