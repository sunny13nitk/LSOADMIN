package com.sap.cds.lsoadmin.UIControllers;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sap.cds.lsoadmin.srv.cockpit.intf.IF_CockpitSrv;

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

    @GetMapping("/")
    public String getCompleteLogHistory()
    {
        if (cpSrv != null)
        {
            List<Esmappmsglog> logs = cpSrv.getCompleteLogHistory();
            if (CollectionUtils.isNotEmpty(logs))
            {
                for (Esmappmsglog esmappmsglog : logs)
                {
                    log.info(esmappmsglog.toString());
                }
            }

        }
        return null;
    }
}
